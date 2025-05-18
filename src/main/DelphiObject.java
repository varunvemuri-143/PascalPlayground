package main;

import java.util.HashMap;
import java.util.Map;

public class DelphiObject {
    private DelphiClass clazz;
    // Object-specific field values.
    private Map<String, Object> fieldValues = new HashMap<>();
    // Field to track whether the object has been destroyed.
    private boolean destroyed = false;

    public DelphiObject(DelphiClass clazz) {
        this.clazz = clazz;
        // Initialize the object's field "privVal" using the class default.
        Object defaultPrivVal = clazz.getDefaultField("privVal");
        fieldValues.put("privVal", (defaultPrivVal != null ? defaultPrivVal : 0));
    }

    public DelphiClass getClassDefinition() {
        return clazz;
    }

    public void setField(String fieldName, Object value) {
        fieldValues.put(fieldName, value);
    }

    public Object getField(String fieldName) {
        return fieldValues.get(fieldName);
    }
    
    // Returns true if the object has been destroyed.
    public boolean isDestroyed() {
        return destroyed;
    }
    
    // Sets the object's destroyed status.
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public String toString() {
        return "DelphiObject[" + clazz.getName() + "]";
    }
}
