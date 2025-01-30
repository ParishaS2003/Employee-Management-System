package persistence;

import org.json.JSONObject;

// Represents an interface for classes that can be converted into JSON format
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
