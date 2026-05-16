package com.smartclinic.dao;

import com.smartclinic.model.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentDAO {

    private static final Logger logger = LogManager.getLogger(AppointmentDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper for basic appointment
    private final RowMapper<Appointment> appointmentRowMapper = (rs, rowNum) -> {
        Appointment a = new Appointment();
        a.setId(rs.getInt("id"));
        a.setPatientId(rs.getInt("patient_id"));
        a.setDoctorId(rs.getInt("doctor_id"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setStatus(rs.getString("status"));
        return a;
    };

    // RowMapper for JOIN query (with patient + doctor names)
    private final RowMapper<Appointment> appointmentDetailMapper = (rs, rowNum) -> {
        Appointment a = new Appointment();
        a.setId(rs.getInt("id"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setStatus(rs.getString("status"));
        a.setPatientName(rs.getString("patient_name"));
        a.setDoctorName(rs.getString("doctor_name"));
        a.setSpecialization(rs.getString("specialization"));
        return a;
    };

    // CREATE
    public int bookAppointment(Appointment appointment) {
        logger.debug("Booking appointment for patient id: {}", appointment.getPatientId());
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                "Scheduled");
    }

    // READ ALL with JOIN
    public List<Appointment> getAllAppointments() {
        logger.debug("Fetching all appointments with JOIN");
        String sql = """
                SELECT a.id, a.appointment_date, a.status,
                       p.name AS patient_name,
                       d.name AS doctor_name,
                       d.specialization
                FROM appointments a
                JOIN patients p ON a.patient_id = p.id
                JOIN doctors d ON a.doctor_id = d.id
                ORDER BY a.appointment_date DESC
                """;
        return jdbcTemplate.query(sql, appointmentDetailMapper);
    }

    // READ BY ID
    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM appointments WHERE id=?";
        List<Appointment> result = jdbcTemplate.query(sql, appointmentRowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    // UPDATE STATUS (cancel or complete)
    public int updateStatus(int id, String status) {
        logger.debug("Updating appointment {} status to {}", id, status);
        String sql = "UPDATE appointments SET status=? WHERE id=?";
        return jdbcTemplate.update(sql, status, id);
    }

    // DELETE
    public int deleteAppointment(int id) {
        logger.debug("Deleting appointment id: {}", id);
        String sql = "DELETE FROM appointments WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
