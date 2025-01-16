package org.golikov.test_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "employee")
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public Timesheet() {}
}
