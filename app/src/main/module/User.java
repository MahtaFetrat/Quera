package module;

import java.util.ArrayList;

public class User {
    protected String username;
    protected String password;
    protected String name;
    protected ArrayList<Class> classes;

    public Class getClassByName(String name) {
        for (Class c : this.classes) {
            if (name.equals(c.name))
                return c;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void addStudentToClass(Class c){
        this.classes.add(c);
    }
}
