package org.golikov.test_app.dto;

import org.golikov.test_app.entity.Employee;

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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
