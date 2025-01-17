package org.golikov.test_app.dto;

import java.util.Date;

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
