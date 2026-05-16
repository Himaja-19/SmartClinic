package com.smartclinic.controller;

import com.smartclinic.dao.DoctorDAO;
import com.smartclinic.model.Appointment;
import com.smartclinic.model.Patient;
import com.smartclinic.service.AppointmentService;
import com.smartclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorDAO doctorDAO;

    // ── Home ──────────────────────────────────────────
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalPatients", patientService.getAllPatients().size());
        model.addAttribute("totalAppointments", appointmentService.getAllAppointments().size());
        return "index";
    }

    // ── Patients ──────────────────────────────────────
    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("newPatient", new Patient());
        return "patients";
    }

    @PostMapping("/patients/register")
    public String registerPatient(@ModelAttribute Patient patient) {
        patientService.registerPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    // ── Appointments ──────────────────────────────────
    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("doctors", doctorDAO.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        return "appointments";
    }

    // FIX: Accept individual form fields instead of model object
    @PostMapping("/appointments/book")
    public String bookAppointment(
            @RequestParam("patientId") int patientId,
            @RequestParam("doctorId") int doctorId,
            @RequestParam("appointmentDate") String appointmentDate,
            Model model) {
        try {
            Appointment appointment = new Appointment(patientId, doctorId, appointmentDate);
            appointmentService.bookAppointment(appointment);
            model.addAttribute("successMsg", "Appointment booked successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMsg", "Error booking appointment: " + e.getMessage());
        }
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("doctors", doctorDAO.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        return "appointments";
    }

    @GetMapping("/appointments/cancel/{id}")
    public String cancelAppointment(@PathVariable int id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments";
    }

    // FIX: Added missing complete endpoint
    @GetMapping("/appointments/complete/{id}")
    public String completeAppointment(@PathVariable int id) {
        appointmentService.completeAppointment(id);
        return "redirect:/appointments";
    }

    // ── Dashboard ─────────────────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorDAO.getAllDoctors());
        return "dashboard";
    }
}
