package com.smartclinic;

import com.smartclinic.dao.PatientDAO;
import com.smartclinic.model.Patient;
import com.smartclinic.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientDAO patientDAO;

    @InjectMocks
    private PatientService patientService;

    private Patient samplePatient;

    @BeforeEach
    public void setUp() {
        samplePatient = new Patient("Himaja R", 22, "himaja@email.com", "9876543210");
        samplePatient.setId(1);
    }

    // Test 1: Register valid patient
    @Test
    public void testRegisterPatient_ValidPatient_Success() {
        when(patientDAO.addPatient(any(Patient.class))).thenReturn(1);
        assertDoesNotThrow(() -> patientService.registerPatient(samplePatient));
        verify(patientDAO, times(1)).addPatient(samplePatient);
    }

    // Test 2: Register patient with empty name should throw exception
    @Test
    public void testRegisterPatient_EmptyName_ThrowsException() {
        Patient invalid = new Patient("", 22, "test@email.com", "9999999999");
        assertThrows(IllegalArgumentException.class,
                () -> patientService.registerPatient(invalid));
        verify(patientDAO, never()).addPatient(any());
    }

    // Test 3: Register patient with invalid age
    @Test
    public void testRegisterPatient_InvalidAge_ThrowsException() {
        Patient invalid = new Patient("Test User", -5, "test@email.com", "9999999999");
        assertThrows(IllegalArgumentException.class,
                () -> patientService.registerPatient(invalid));
    }

    // Test 4: Get all patients returns correct list
    @Test
    public void testGetAllPatients_ReturnsPatientList() {
        List<Patient> mockList = Arrays.asList(
                samplePatient,
                new Patient("Arjun K", 30, "arjun@email.com", "9123456789")
        );
        when(patientDAO.getAllPatients()).thenReturn(mockList);

        List<Patient> result = patientService.getAllPatients();

        assertEquals(2, result.size());
        assertEquals("Himaja R", result.get(0).getName());
        verify(patientDAO, times(1)).getAllPatients();
    }

    // Test 5: Get patient by ID returns correct patient
    @Test
    public void testGetPatientById_ValidId_ReturnsPatient() {
        when(patientDAO.getPatientById(1)).thenReturn(samplePatient);

        Patient result = patientService.getPatientById(1);

        assertNotNull(result);
        assertEquals("Himaja R", result.getName());
        assertEquals(22, result.getAge());
    }

    // Test 6: Get patient by non-existent ID returns null
    @Test
    public void testGetPatientById_InvalidId_ReturnsNull() {
        when(patientDAO.getPatientById(999)).thenReturn(null);
        Patient result = patientService.getPatientById(999);
        assertNull(result);
    }

    // Test 7: Delete patient calls DAO correctly
    @Test
    public void testDeletePatient_CallsDAO() {
        when(patientDAO.deletePatient(1)).thenReturn(1);
        patientService.deletePatient(1);
        verify(patientDAO, times(1)).deletePatient(1);
    }
}
