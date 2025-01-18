package org.golikov.test_app.controllers;

import org.golikov.test_app.dto.EmployeeDTO;
import org.golikov.test_app.exceptions.NoSuchValueException;
import org.golikov.test_app.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class ApiEmployeesController {

    private final EmployeeService employeeService;
    public ApiEmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployee(id);
        if (employeeDTO == null) {
            throw new NoSuchValueException("Employee with id " + id + " not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }
}
