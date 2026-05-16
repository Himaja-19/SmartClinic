package com.smartclinic.service;

import com.smartclinic.dao.PatientDAO;
import com.smartclinic.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final Logger logger = LogManager.getLogger(PatientService.class);

    @Autowired
    private PatientDAO patientDAO;

    public void registerPatient(Patient patient) {
        logger.info("Registering new patient: {}", patient.getName());
        if (patient.getName() == null || patient.getName().isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be empty");
        }
        if (patient.getAge() <= 0 || patient.getAge() > 120) {
            throw new IllegalArgumentException("Invalid age: " + patient.getAge());
        }
        patientDAO.addPatient(patient);
        logger.info("Patient registered successfully: {}", patient.getName());
    }

    public List<Patient> getAllPatients() {
        logger.info("Fetching all patients");
        return patientDAO.getAllPatients();
    }

    public Patient getPatientById(int id) {
        logger.info("Fetching patient by id: {}", id);
        return patientDAO.getPatientById(id);
    }

    public void updatePatient(Patient patient) {
        logger.info("Updating patient id: {}", patient.getId());
        patientDAO.updatePatient(patient);
    }

    public void deletePatient(int id) {
        logger.info("Deleting patient id: {}", id);
        patientDAO.deletePatient(id);
    }
}
