package main;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> vars = new HashMap<>();
    private Environment parent;

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public void setVariable(String name, Object value) {
        vars.put(name, value);
    }

    public Object getVariable(String name) {
        if (vars.containsKey(name)) {
            return vars.get(name);
        } else if (parent != null) {
            return parent.getVariable(name);
        } else {
            System.err.println("Warning: variable '" + name + "' not defined. Defaulting to 0.");
            return 0;
        }
    }
    
    // New: Returns a variable only if it is declared in THIS environment (not inherited)
    public Object getOwnVariable(String name) {
        return vars.get(name);
    }
}
