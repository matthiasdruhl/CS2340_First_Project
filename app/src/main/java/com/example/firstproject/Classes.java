package com.example.firstproject;

import java.util.ArrayList;

public class Classes {
    private static ArrayList<Class> classes = new ArrayList<>();

    public static ArrayList<Class> getClasses(){
        return classes;
    }

    public static Class get(int i) {
        return classes.get(i);
    }

    public static void addClass(Class c){
        classes.add(c);
    }

    public static Class getClassByName(String name) {
        for (Class c : classes) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static void deleteClass(Class test) {
        classes.remove(test);
    }
}
