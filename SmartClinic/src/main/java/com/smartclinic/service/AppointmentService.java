package com.smartclinic.service;

import com.smartclinic.dao.AppointmentDAO;
import com.smartclinic.model.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private static final Logger logger = LogManager.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentDAO appointmentDAO;

    public void bookAppointment(Appointment appointment) {
        logger.info("Booking appointment: patient={}, doctor={}",
                appointment.getPatientId(), appointment.getDoctorId());
        if (appointment.getAppointmentDate() == null || appointment.getAppointmentDate().isBlank()) {
            throw new IllegalArgumentException("Appointment date is required");
        }
        appointmentDAO.bookAppointment(appointment);
        logger.info("Appointment booked successfully");
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    public Appointment getAppointmentById(int id) {
        return appointmentDAO.getAppointmentById(id);
    }

    public void cancelAppointment(int id) {
        logger.info("Cancelling appointment id: {}", id);
        appointmentDAO.updateStatus(id, "Cancelled");
    }

    public void completeAppointment(int id) {
        logger.info("Completing appointment id: {}", id);
        appointmentDAO.updateStatus(id, "Completed");
    }

    public void deleteAppointment(int id) {
        logger.info("Deleting appointment id: {}", id);
        appointmentDAO.deleteAppointment(id);
    }
}
