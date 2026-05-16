package com.smartclinic.dao;

import com.smartclinic.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Doctor> doctorRowMapper = (rs, rowNum) -> new Doctor(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("specialization"),
            rs.getString("available_days"),
            rs.getString("email")
    );

    public List<Doctor> getAllDoctors() {
        return jdbcTemplate.query("SELECT * FROM doctors ORDER BY name", doctorRowMapper);
    }

    public Doctor getDoctorById(int id) {
        List<Doctor> result = jdbcTemplate.query(
                "SELECT * FROM doctors WHERE id=?", doctorRowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }
}
