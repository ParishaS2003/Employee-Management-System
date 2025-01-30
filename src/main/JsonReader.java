package persistence;

import model.Employee;
import model.EmployeeManagement;
import model.WorkPlace;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// reads employee data from JSON data stored in a file
// Class modelled on JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads EmployeeManagement data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EmployeeManagement read() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEmployeeManagement(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    // EFFECTS: parses EmployeeManagement data from JSON object and returns it
    public EmployeeManagement parseEmployeeManagement(JSONObject jsonObject) {
        EmployeeManagement employeeManagement = new EmployeeManagement(new ArrayList<>(), new ArrayList<>());
        addEmployees(employeeManagement, jsonObject.getJSONArray("workPlaceManagement"));
        return employeeManagement;
    }

    //MODIFIES: employeeManagement
    // EFFECTS: Parses employee objects from the JSON array and adds them to the EmployeeManagement
    public void addEmployees(EmployeeManagement employeeManagement, JSONArray workplaceArray) {
        for (Object obj : workplaceArray) {
            JSONObject workPlaceJson = (JSONObject) obj;
            String workPlaceName = workPlaceJson.getString("workPlaceName");
            String location = workPlaceJson.getString("location");
            WorkPlace workPlace = new WorkPlace(workPlaceName, location, new ArrayList<>());

            JSONArray workplaceEmployeesJson = workPlaceJson.getJSONArray("employees");
            for (Object employeeObject : workplaceEmployeesJson) {
                JSONObject employeeJson = (JSONObject) employeeObject;
                String name = employeeJson.getString("name");
                String jobTitle = employeeJson.getString("jobTitle");
                int timeAvailability = employeeJson.getInt("timeAvailability");
                double wage = employeeJson.getDouble("wage");
                Employee employee = new Employee(name, jobTitle, timeAvailability, wage, workPlace);
                workPlace.addEmployee(employee);
            }
            employeeManagement.addWorkPlace(workPlace);
        }
    }
}


