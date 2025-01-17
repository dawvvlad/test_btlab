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

}
