package com.example.android_fitness_app.Model;

import java.net.URL;
import java.util.ArrayList;

public class Exercise {
    private static ArrayList<Exercise> allExercise = new ArrayList<>();
    private String info;
    private String name;
    private URL imageUrl;

    public Exercise(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public static ArrayList<Exercise> getAllExercise() {
        return allExercise;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public static void addExercise(Exercise exercise) {
        allExercise.add(exercise);
    }
}

