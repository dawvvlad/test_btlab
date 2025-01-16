package org.golikov.test_app.dto;

import lombok.Data;
import org.golikov.test_app.entity.Employee;

@Data
public class EmployeeDTO {
    private Long id;
    private String lastName;
    private String firstName;

    public EmployeeDTO() {}
    public EmployeeDTO(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.lastName = employee.getLastName();
        this.firstName = employee.getFirstName();
    }
}
