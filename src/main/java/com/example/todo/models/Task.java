package com.example.todo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")  // This maps the class to the "tasks" table in the database
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT in MySQL
    @Column(name = "id")  // Specifies the column name
    private long id;

    @Column(name = "description", nullable = false)  // Maps to the description column
    private String description;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'TODO'")  // Maps to the status column with a default value
    private String status;

    @Column(name = "start_date", nullable = false)  // Maps to the start_date column
    @Temporal(TemporalType.DATE)  // Specifies that the field is a Date
    private Date startDate;

    @Column(name = "target_date", nullable = false)  // Maps to the target_date column
    @Temporal(TemporalType.DATE)  // Specifies that the field is a Date
    private Date targetDate;

    // Constructor
    public Task(long id, String description, String status, Date startDate, Date targetDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    // Default constructor
    public Task() {
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", targetDate=" + targetDate +
                '}';
    }
}
