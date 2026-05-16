package com.smartclinic.dao;

import com.smartclinic.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PatientDAO {

    private static final Logger logger = LogManager.getLogger(PatientDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to Patient object
    private final RowMapper<Patient> patientRowMapper = (rs, rowNum) -> {
        Patient p = new Patient();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setAge(rs.getInt("age"));
        p.setEmail(rs.getString("email"));
        p.setPhone(rs.getString("phone"));
        return p;
    };

    // CREATE
    public int addPatient(Patient patient) {
        logger.debug("Adding patient: {}", patient.getName());
        String sql = "INSERT INTO patients (name, age, email, phone) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                patient.getName(),
                patient.getAge(),
                patient.getEmail(),
                patient.getPhone());
    }

    // READ ALL
    public List<Patient> getAllPatients() {
        logger.debug("Fetching all patients");
        String sql = "SELECT * FROM patients ORDER BY id DESC";
        return jdbcTemplate.query(sql, patientRowMapper);
    }

    // READ BY ID
    public Patient getPatientById(int id) {
        logger.debug("Fetching patient with id: {}", id);
        String sql = "SELECT * FROM patients WHERE id = ?";
        List<Patient> result = jdbcTemplate.query(sql, patientRowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    // UPDATE
    public int updatePatient(Patient patient) {
        logger.debug("Updating patient id: {}", patient.getId());
        String sql = "UPDATE patients SET name=?, age=?, email=?, phone=? WHERE id=?";
        return jdbcTemplate.update(sql,
                patient.getName(),
                patient.getAge(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getId());
    }

    // DELETE
    public int deletePatient(int id) {
        logger.debug("Deleting patient id: {}", id);
        String sql = "DELETE FROM patients WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
