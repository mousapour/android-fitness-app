package com.example.android_fitness_app.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Initial {
    public static void initialExercise() {
        String file = "/Model/Resource/Book1.csv";
        String line;
        ArrayList<String> allAnswers = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] pharse = line.split("\\$");
                allAnswers.add(pharse[0]);
                String ans = "";
                for (int i = 1; i < pharse.length - 1; i++) {
                    ans += pharse[i];
                }
                allAnswers.add(ans);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < allAnswers.size(); i++) {
            if (allAnswers.get(i).charAt(0) == ',') {
                allAnswers.set(i, allAnswers.get(i).substring(1));
            }
            if (allAnswers.get(i).charAt(0) == '"') {
                allAnswers.set(i, allAnswers.get(i).substring(1));
            }
        }
        for(int i=0;i<allAnswers.size();i+=2){
            Exercise.addExercise(new Exercise(allAnswers.get(i),allAnswers.get(i+1)));
        }
    }
}
