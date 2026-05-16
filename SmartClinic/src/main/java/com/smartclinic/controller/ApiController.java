package com.smartclinic.controller;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.Patient;
import com.smartclinic.service.AppointmentService;
import com.smartclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    // GET all appointments
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // GET appointment by ID
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Appointment a = appointmentService.getAppointmentById(id);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(a);
    }

    // POST book appointment
    @PostMapping("/appointments")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        appointmentService.bookAppointment(appointment);
        return ResponseEntity.ok("Appointment booked successfully");
    }

    // PUT cancel appointment
    @PutMapping("/appointments/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable int id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment cancelled");
    }

    // PUT complete appointment
    @PutMapping("/appointments/{id}/complete")
    public ResponseEntity<String> completeAppointment(@PathVariable int id) {
        appointmentService.completeAppointment(id);
        return ResponseEntity.ok("Appointment marked as completed");
    }

    // DELETE appointment
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted");
    }

    // GET all patients
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // POST register patient
    @PostMapping("/patients")
    public ResponseEntity<String> registerPatient(@RequestBody Patient patient) {
        patientService.registerPatient(patient);
        return ResponseEntity.ok("Patient registered successfully");
    }
}
