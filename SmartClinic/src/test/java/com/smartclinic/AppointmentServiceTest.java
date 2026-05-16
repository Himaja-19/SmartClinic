package com.smartclinic;

import com.smartclinic.dao.AppointmentDAO;
import com.smartclinic.model.Appointment;
import com.smartclinic.service.AppointmentService;
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
public class AppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;

    @InjectMocks
    private AppointmentService appointmentService;

    // Test 1: Book valid appointment
    @Test
    public void testBookAppointment_ValidData_Success() {
        Appointment a = new Appointment(1, 1, "2026-06-01");
        when(appointmentDAO.bookAppointment(any())).thenReturn(1);
        assertDoesNotThrow(() -> appointmentService.bookAppointment(a));
        verify(appointmentDAO, times(1)).bookAppointment(a);
    }

    // Test 2: Book appointment with no date throws exception
    @Test
    public void testBookAppointment_NoDate_ThrowsException() {
        Appointment a = new Appointment(1, 1, "");
        assertThrows(IllegalArgumentException.class,
                () -> appointmentService.bookAppointment(a));
        verify(appointmentDAO, never()).bookAppointment(any());
    }

    // Test 3: Get all appointments
    @Test
    public void testGetAllAppointments_ReturnsList() {
        Appointment a1 = new Appointment(1, 1, "2026-06-01");
        Appointment a2 = new Appointment(2, 2, "2026-06-02");
        when(appointmentDAO.getAllAppointments()).thenReturn(Arrays.asList(a1, a2));

        List<Appointment> result = appointmentService.getAllAppointments();
        assertEquals(2, result.size());
    }

    // Test 4: Cancel appointment calls updateStatus correctly
    @Test
    public void testCancelAppointment_CallsUpdateStatus() {
        when(appointmentDAO.updateStatus(1, "Cancelled")).thenReturn(1);
        appointmentService.cancelAppointment(1);
        verify(appointmentDAO, times(1)).updateStatus(1, "Cancelled");
    }

    // Test 5: Complete appointment
    @Test
    public void testCompleteAppointment_CallsUpdateStatus() {
        when(appointmentDAO.updateStatus(2, "Completed")).thenReturn(1);
        appointmentService.completeAppointment(2);
        verify(appointmentDAO, times(1)).updateStatus(2, "Completed");
    }
}
