package org.golikov.test_app.dto;

import java.util.Date;

public class TimesheetCreateRequest {
    private Long id;
    private Integer reason;
    private Long employeeId;
    private Date startDate;
    private Integer duration;
    private String description;
    private Boolean discounted;

    public TimesheetCreateRequest() {}
    public TimesheetCreateRequest(TimesheetDTO timesheetDTO) {
        this.id = timesheetDTO.getId();
        this.reason = timesheetDTO.getReason();
        this.description = timesheetDTO.getDescription();
        this.discounted = timesheetDTO.getDiscounted();
        this.startDate = timesheetDTO.getStartDate();
        this.duration = timesheetDTO.getDuration();
        this.employeeId = timesheetDTO.getEmployee().getId();
    }

    public Long getEmployeeId() {
        return employeeId;
    }
    public Integer getReason() {
        return reason;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Integer getDuration() {
        return duration;
    }
    public String getDescription() {
        return description;
    }
    public Boolean getDiscounted() {
        return discounted;
    }
    public Long getId() {
        return id;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public void setReason(Integer reason) {
        this.reason = reason;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDiscounted(Boolean discounted) {
        this.discounted = discounted;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

