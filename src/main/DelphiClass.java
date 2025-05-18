package main;

import java.util.*;

public class DelphiClass {
    public enum Visibility { PUBLIC, PROTECTED, PRIVATE }

    private String name;
    private DelphiClass parent;
    private Method constructor;
    private Method destructor;

    // Track field visibilities and defaults
    private Map<String, Visibility> fieldVisibility = new HashMap<>();
    private Map<String, Object> defaultFields = new HashMap<>();

    // NEW: Track instance methods declared in this class
    private Map<String, Method> methods = new HashMap<>();

    public DelphiClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(DelphiClass parent) {
        this.parent = parent;
    }

    public DelphiClass getParent() {
        return parent;
    }

    public void setConstructor(Method cons) {
        this.constructor = cons;
    }

    public Method getConstructor() {
        return constructor;
    }

    public void setDestructor(Method dest) {
        this.destructor = dest;
    }

    public Method getDestructor() {
        return destructor;
    }

    public void addField(String fieldName, Visibility vis, Object defaultValue) {
        fieldVisibility.put(fieldName, vis);
        defaultFields.put(fieldName, defaultValue);
    }

    public Visibility getFieldVisibility(String fieldName) {
        Visibility vis = fieldVisibility.get(fieldName);
        if (vis == null && parent != null) {
            return parent.getFieldVisibility(fieldName);
        }
        return vis;
    }

    public Object getDefaultField(String fieldName) {
        Object v = defaultFields.get(fieldName);
        if (v == null && parent != null) {
            return parent.getDefaultField(fieldName);
        }
        return v;
    }

    // NEW: Register an instance method under this class
    public void addMethod(String methodName, Method method) {
        methods.put(methodName, method);
    }

    // NEW: Retrieve an instance method, checking this class then its parent
    public Method getMethod(String methodName) {
        Method m = methods.get(methodName);
        if (m == null && parent != null) {
            return parent.getMethod(methodName);
        }
        return m;
    }
}
