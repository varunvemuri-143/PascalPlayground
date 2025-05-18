package main;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Stack<Environment> envStack = new Stack<>();
    private Map<String, DelphiClass> classes = new HashMap<>();

    public SymbolTable() {
        // Push the global environment.
        envStack.push(new Environment(null));
    }

    public Environment currentEnvironment() {
        return envStack.peek();
    }

    public void pushScope() {
        envStack.push(new Environment(envStack.peek()));
    }
    
    // Push a new scope with a specified parent (for static scoping)
    public void pushScope(Environment parent) {
        envStack.push(new Environment(parent));
    }

    public void popScope() {
        if (envStack.size() > 1) {
            envStack.pop();
        }
    }

    // Set a variable in the current (top) scope.
    public void setVariable(String name, Object value) {
        envStack.peek().setVariable(name, value);
    }
    
    // Update a variable in the environment where it was declared.
    public void updateVariable(String name, Object value) {
        // Loop from top to bottom of the stack.
        for (int i = envStack.size() - 1; i >= 0; i--) {
            Environment env = envStack.get(i);
            // If this environment has the variable declared.
            if (env.getOwnVariable(name) != null) {
                env.setVariable(name, value);
                return;
            }
        }
        // If not found, update in the global environment.
        envStack.firstElement().setVariable(name, value);
    }

    // Search from the most local scope to the global scope for the variable.
    public Object getVariable(String name) {
        for (int i = envStack.size() - 1; i >= 0; i--) {
            Object value = envStack.get(i).getOwnVariable(name);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public void setClass(String name, DelphiClass clazz) {
        classes.put(name, clazz);
    }

    public DelphiClass getClass(String name) {
        return classes.get(name);
    }
}
