package model;

// This class called "Employee" represents a single employee in the workplace
// shift scheduling system. An employee has a name, job title, and available
// working hours. They can be added to a workplace.

import org.json.JSONObject;
import persistence.Writable;


public class Employee implements Writable {
    private String name;                        // Employee's full name
    private String jobTitle;                    // Employee's job title
    private int timeAvailability;               // Employee's humber of hours availability in a day
    private double wage;                        // Employee's wage per hour worked
    private WorkPlace workPlace;                // Employee's place of Work

    // REQUIRES: name and job cannot be empty strings, wage > 0, timeAvailability
    // should be a real time in 24-hour format
    // MODIFIES: this
    // EFFECTS: Constructs an object called Employee with a name, job title,
    // time availability, and per hour wage
    public Employee(String name, String jobTitle, int timeAvailability,
                    double wage, WorkPlace workPlace) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.timeAvailability = timeAvailability;
        this.wage = wage;
        this.workPlace = workPlace;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getTimeAvailability() {
        return timeAvailability;
    }

    public void setTimeAvailability(int timeAvailability) {
        this.timeAvailability = timeAvailability;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }

    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: calculates the projected salary of the employee by
    // multiplying the hours worked with the per hour wage
    public double salary(double wage, int timeAvailability) {
        return (wage * timeAvailability);
    }


    // EFFECTS: returns a string representation of an Employee
    @Override
    public String toString() {
        return "Employee name: " + name + ", Job Title: " + jobTitle + ", "
                + "time availability: " + timeAvailability + ", Wage: " + wage;
    }

    // MODIFIES: this
    // EFFECTS: Returns a string representation of the Employee object and associated details
    // in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("jobTitle", jobTitle);
        json.put("timeAvailability", timeAvailability);
        json.put("wage", wage);
        json.put("workplace", workPlace);
        return json;
    }
}




