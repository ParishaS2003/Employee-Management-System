package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// This class called EmployeeManagement manages Employee Schedules in a Work Place.
// It contains a list of all Employees and a list of all WorkPlaces
public class EmployeeManagement implements Writable {
    private ArrayList<Employee> employees;
    private ArrayList<WorkPlace> workPlaces;
    private EventLog eventLog;


    // MODIFIES: this
    // EFFECTS: Constructs an object called EmployeeManagement with a
    // list of employees and a list of work places
    public EmployeeManagement(ArrayList<Employee> employees, ArrayList<WorkPlace> workPlaces) {
        this.employees = employees;
        this.workPlaces = workPlaces;
        this.eventLog = EventLog.getInstance();
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setWorkPlaces(ArrayList<WorkPlace> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public ArrayList<WorkPlace> getWorkPlaces() {
        return workPlaces;
    }


    // MODIFIES: this
    // EFFECTS: List all the employees in a workplace
    public List<Employee> listEmployeesInWorkplace(WorkPlace workPlace) {
        if (!workPlaces.contains(workPlace)) {
            throw new IllegalArgumentException("Workplace does not exist.");
        }
        return workPlace.getEmployees();
    }

    // MODIFIES: this
    // EFFECTS: Adds an employee to a list of employees.
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }


    // MODIFIES: this
    // EFFECTS: Removes an employee from a list of employees (and from workplace)
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        for (WorkPlace workPlace : workPlaces) {
            if (workPlace.getEmployees().contains(employee)) {
                workPlace.removeEmployee(employee);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a Work Place to a list of Work Places.
    public void addWorkPlace(WorkPlace workPlace) {
        workPlaces.add(workPlace);
    }

    // MODIFIES: this
    // EFFECTS: Removes a Work Place from a list of Work Places.
    public void removeWorkPlace(WorkPlace workPlace) {
        workPlaces.remove(workPlace);
    }


    // REQUIRES: Name should not be an empty string
    // MODIFIES: None
    // EFFECTS: Returns the Employee by name
    public Employee getEmployeeByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        throw new IllegalArgumentException("Employee with name " + name + " not found.");
    }

    // REQUIRES: Name should not be an empty string
    // MODIFIES: None
    // EFFECTS: Returns the Work Place by name
    public WorkPlace getPlaceByName(String name) {
        for (WorkPlace workPlace : workPlaces) {
            if (workPlace.getWorkPlaceName().equals(name)) {
                return workPlace;
            }
        }
        throw new IllegalArgumentException("Workplace with name " + name + " not found.");
    }


    // REQUIRES: employeesToAdd should not be empty
    // MODIFIES: None
    // EFFECTS: Add multiple employees to a workplace
    public WorkPlace addEmployeesToWorkplace(List<Employee> employeesToAdd, WorkPlace workPlace) {
        boolean workplaceExists = workPlaces.contains(workPlace);
        if (!workplaceExists) {
            return null;
        }
        for (Employee employee : employeesToAdd) {
            boolean employeeExists = employees.contains(employee);
            if (!employeeExists) {
                return null;
            }
            if (workPlace.getEmployees().contains(employee)) {
                return null;
            }
            workPlace.addEmployee(employee);
        }
        return workPlace;
    }

    // Returns a string representation of the list of family contacts, in JSON format
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workPlaceManagement", employeeManagementToJsonArray());
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECTS: Returns an array of JSON type representing the list of family contacts
    public JSONArray employeeManagementToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (WorkPlace workPlace : this.workPlaces) {
            jsonArray.put(workPlace.toJson());
        }
        return jsonArray;
    }






    // MODIFIES: this
    // EFFECTS: Logs an event indicating that a workplace has been added
    // Creates and logs the event in the event log with a specific relevant message
    public void logAddWorkplaceEvent() {
        String eventDescription = "\t\t Workplace added to Management System.";
        Event logEvent = new Event(eventDescription);
        eventLog.logEvent(logEvent);
    }

    // MODIFIES: this
    // EFFECTS: Logs an event indicating that a search for Employee details has been performed
    // Creates and logs the event in the event log with a specific relevant message
    public void logEmployeeDetailsEvent() {
        String eventDescription = "\t\t Searched for the details of an employee.";
        Event logEvent = new Event(eventDescription);
        eventLog.logEvent(logEvent);
    }


    // MODIFIES: this
    // EFFECTS: Logs an event indicating that employees of a workplace have been listed
    // Creates and logs the event in the event log with a specific relevant message
    public void logListEmployees() {
        String eventDescription = "\t\t Viewed the employees of a particular workplace.";
        Event logEvent = new Event(eventDescription);
        eventLog.logEvent(logEvent);
    }

    // MODIFIES: this
    // EFFECTS: Logs an event indicating that employees of a workplace have been listed
    // Creates and logs the event in the event log with a specific relevant message
    public void logListAllEmployees() {
        String eventDescription = "\t\t Viewed all the employees in the System.";
        Event logEvent = new Event(eventDescription);
        eventLog.logEvent(logEvent);
    }

    // REQUIRES: loadWorkplaces() method from FamilyContactManagerGUI class works
    // MODIFIES: this
    // EFFECTS: Logs an event indicating that the workplaces have been loaded from a JSON file
    // Creates and logs the event in the event log with a specific relevant message
    public void logLoadWorkplaceEvent() {
        String eventDescription = "\t\t Loaded the workplaces from a JSON file.";
        Event logEvent = new Event(eventDescription);
        eventLog.logEvent(logEvent);
    }

    // REQUIRES: saveWorkplaces() method from FamilyContactManagerGUI class works
    // MODIFIES: this
    // EFFECTS: Logs an event indicating that the contact(s) have been saved to a JSON file
    // Creates and logs the event in the event log with a specific relevant message
    public void logSaveWorkplaceEvent() {
        String eventDescription = "\t\t Saved the workplaces to a JSON file.";
        Event logEvent = new Event(eventDescription);
        eventLog.logEvent(logEvent);
    }

}