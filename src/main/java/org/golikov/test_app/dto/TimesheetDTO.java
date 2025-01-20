package org.golikov.test_app.dto;

import lombok.ToString;
import org.golikov.test_app.entity.Employee;
import org.golikov.test_app.entity.Timesheet;

import java.util.Date;

@ToString
public class TimesheetDTO {
    private Long id;
    private Integer reason;
    private EmployeeDTO employee;
    private Date startDate;
    private Integer duration;
    private String description;
    private Boolean discounted;

    public TimesheetDTO() {}
    public TimesheetDTO(Long id, Integer reason, EmployeeDTO employee, Date startDate, Integer duration, String description, Boolean discounted) {
        this.id = id;
        this.reason = reason;
        this.employee = employee;
        this.startDate = startDate;
        this.duration = duration;
        this.description = description;
        this.discounted = discounted;
    }

    public TimesheetDTO(Timesheet timesheet) {
        this.id = timesheet.getId();
        this.reason = timesheet.getReason();
        this.employee = new EmployeeDTO(timesheet.getEmployee());
        this.startDate = timesheet.getStartDate();
        this.duration = timesheet.getDuration();
        this.description = timesheet.getDescription();
        this.discounted = timesheet.getDiscounted();
    }

    public Timesheet convertToEntity() {
        Timesheet timesheet = new Timesheet();

        if (this.employee != null && this.employee.getId() != null) {
            Employee employee = new Employee();
            employee.setId(this.employee.getId());
            timesheet.setEmployee(employee);
        }

        timesheet.setId(this.id);
        timesheet.setReason(this.reason);
        timesheet.setDescription(this.description);
        timesheet.setStartDate(this.startDate);
        timesheet.setDuration(this.duration);
        timesheet.setDiscounted(this.discounted);

        return timesheet;
    }

    // геттеры и сеттеры
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public EmployeeDTO getEmployee() {
        return employee;
    }
    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }
    public Integer getReason() {
        return reason;
    }
    public void setReason(Integer reason) {
        this.reason = reason;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Boolean getDiscounted() {
        return discounted;
    }
    public void setDiscounted(Boolean discounted) {
        this.discounted = discounted;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
