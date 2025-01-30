package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// This class called "Workplace" represents a single place of work. For example, a coffee shop
// or a gym. It has information about the name of organisation, the type of organisation, and number of
// employees working there.

public class WorkPlace implements Writable {
    private String workPlaceName;  // Name of the organization
    private String location;       // Location of the organisation
    private List<Employee> employees;


    // REQUIRES: workPlaceName and location cannot be empty strings
    // MODIFIES: this
    // EFFECTS: Constructs an object called WorkPlace with a name, location,
    // and list of employees

    public WorkPlace(String workPlaceName, String location, ArrayList<Employee> employees) {
        this.workPlaceName = workPlaceName;
        this.location = location;
        this.employees = employees != null ? employees : new ArrayList<>();
    }

    public String getWorkPlaceName() {
        return workPlaceName;
    }

    public void setWorkPlaceName(String workPlaceName) {
        this.workPlaceName = workPlaceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    // MODIFIES: this
    // EFFECTS: Adds an employee to a work place
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setWorkPlace(this);
    }

    // MODIFIES: this
    // EFFECTS: Removes an employee from a work place
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setWorkPlace(null);
    }


    // EFFECTS: returns a string representation of a Work Place
    @Override
    public String toString() {
        return "WorkPlace name: " + workPlaceName + ", Workplace location: " + location + ", "
                + "WorkPlace Employees: " + employees;
    }

    // MODIFIES: this
    // EFFECTS: Returns a string representation of the WorkPlace object in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workPlaceName", workPlaceName);
        json.put("location", location);
        JSONArray workplaceEmployeesArray = new JSONArray();
        for (Employee employee : employees) {
            workplaceEmployeesArray.put(employee.toJson());
        }
        json.put("employees", workplaceEmployeesArray);
        return json;
    }
}
