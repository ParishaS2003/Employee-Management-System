package persistence;

import model.Employee;
import model.EmployeeManagement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

// writes JSON representation of employee management data to file
// Class modelled on JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of employee management data to file
    public void write(EmployeeManagement employeeManagement) {
        JSONObject json = employeeManagement.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {

        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {

        writer.print(json);
    }

}