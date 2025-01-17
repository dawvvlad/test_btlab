package org.golikov.test_app.service;

import org.golikov.test_app.dto.EmployeeDTO;
import org.golikov.test_app.entity.Employee;
import org.golikov.test_app.repos.EmployeeRepository;
import org.golikov.test_app.service.employee.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employeeDTO);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        Long id = 1L;

        doNothing().when(employeeRepository).deleteById(id);

        assertDoesNotThrow(() -> employeeService.deleteEmployee(id));

        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetEmployee() {
        Long id = 1L;

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeRepository.getReferenceById(id)).thenReturn(employee);

        EmployeeDTO result = employeeService.getEmployee(id);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(employeeRepository, times(1)).getReferenceById(id);

        System.out.println(result);
    }

    @Test
    void testGetAllEmployees_EmptyList() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(employeeRepository, times(1)).findAll();
    }
}
