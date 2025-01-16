package org.golikov.test_app.service.employee;

import org.golikov.test_app.dto.EmployeeDTO;
import org.golikov.test_app.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO getEmployee(Long id);
    List<EmployeeDTO> getAllEmployees();
}
