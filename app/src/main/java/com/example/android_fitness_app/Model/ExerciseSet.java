package com.example.android_fitness_app.Model;

import java.util.ArrayList;

public class ExerciseSet {
    private final Exercise exercise;
    private int reps;
    private double weight;

    public ExerciseSet(Exercise exercise) {
        this.exercise = exercise;
        this.reps = 0;
        this.weight = 0;
    }

    public static String getBestSetString(ArrayList<ExerciseSet> exerciseSets) {
        ExerciseSet bestSet = exerciseSets.get(0);
        for (ExerciseSet set : exerciseSets) {
            if (set.getVolume() > bestSet.getVolume()) {
                bestSet = set;
            }
        }
        return bestSet.getWeight() + " kg * " + bestSet.getReps();
    }

    public double getVolume() {
        return reps * weight;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return exercise.getName() +
                "\t" + weight +
                " * " + reps;
    }
}
