package org.golikov.test_app.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "employee")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Employee employee;

    @Column(name = "reason")
    private Integer reason;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "discounted")
    private Boolean discounted;

    @Column(name = "description")
    private String description;

    public Timesheet() {}



    // геттеры и сеттеры

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
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
