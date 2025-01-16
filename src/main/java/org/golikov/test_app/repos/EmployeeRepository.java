package org.golikov.test_app.repos;

import org.golikov.test_app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getEmployeesById(Long id);
    void deleteById(Long id);
}
