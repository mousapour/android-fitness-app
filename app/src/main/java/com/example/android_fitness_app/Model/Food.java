package com.example.android_fitness_app.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Food {
    // 1db9da6bdc994e57b2f90a5e7cf06c9c
    private String foodName;
    private String amountOfCalories;
    private String amountOfCarbohydrates;
    private String amountOfProtein;
    private String amountOfFat;
    private String percentOfFat;
    private String percentOfProtein;
    private String percentOfCarbohydrate;

    public Food(String foodName, String amountOfCalories, String amountOfCarbohydrates,
                String amountOfProtein, String amountOfFat, String percentOfFat,
                String percentOfProtein, String percentOfCarbohydrate) {
        this.foodName = foodName;
        this.amountOfCalories = amountOfCalories;
        this.amountOfCarbohydrates = amountOfCarbohydrates;
        this.amountOfProtein = amountOfProtein;
        this.amountOfFat = amountOfFat;
        this.percentOfFat = percentOfFat;
        this.percentOfProtein = percentOfProtein;
        this.percentOfCarbohydrate = percentOfCarbohydrate;
    }

    private static String getIdOfFood(String foodName) {
        String API_KEY = "1db9da6bdc994e57b2f90a5e7cf06c9c";
        String urlString = "https://api.spoonacular.com/food/menuItems/search?query=" + foodName +
                "&number=1" + "&apiKey=" + API_KEY;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            String allData = result.toString();
            return allData.substring(allData.indexOf("id") + 4, allData.indexOf(",",
                    allData.indexOf("id") + 4));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Food getFoodByName(String foodName) {
        String API_KEY = "1db9da6bdc994e57b2f90a5e7cf06c9c";
        String id = getIdOfFood(foodName);
        String urlString = "https://api.spoonacular.com/food/menuItems/" + id + "?apiKey=" + API_KEY;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            String allData = result.toString();
            System.out.println(allData);
            return getFoodDetailFromJson(allData);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Food getFoodDetailFromJson(String json) {
        String name = json.substring(json.indexOf("title") + 8, json.indexOf("\"",
                json.indexOf("title") + 8));
        String amountOfCarbohydrates = json.substring(json.indexOf("Carbohydrates") + 24
                , json.indexOf(",", json.indexOf("Carbohydrates") + 24));
        String amountOfCalories = json.substring(json.indexOf("Calories") + 19,
                json.indexOf(",", json.indexOf("Calories") + 19));
        String amountOfFat = json.substring(json.indexOf("Fat") + 14,
                json.indexOf(",", json.indexOf("Fat") + 14));
        String amountOfProtein = json.substring(json.indexOf("Protein") + 18,
                json.indexOf(",", json.indexOf("Protein") + 18));
        String percentOfPCarbs = json.substring(json.indexOf("percentCarbs") + 14,
                json.indexOf("}", json.indexOf("percentCarbs") + 14));
        String percentOfFat = json.substring(json.indexOf("percentFat") + 12,
                json.indexOf(",", json.indexOf("percentFat") + 12));
        String percentOfProtein = json.substring(json.indexOf("percentProtein") + 16,
                json.indexOf(",", json.indexOf("percentProtein") + 16));
        return new Food(name,amountOfCalories,amountOfCarbohydrates,amountOfProtein,amountOfFat,
                percentOfFat,percentOfProtein,percentOfPCarbs);
    }

    public String getFoodName() {
        return foodName;
    }

    public String getAmountOfCalories() {
        return amountOfCalories;
    }

    public String getAmountOfCarbohydrates() {
        return amountOfCarbohydrates;
    }

    public String getAmountOfProtein() {
        return amountOfProtein;
    }

    public String getAmountOfFat() {
        return amountOfFat;
    }

    public String getPercentOfFat() {
        return percentOfFat;
    }

    public String getPercentOfProtein() {
        return percentOfProtein;
    }

    public String getPercentOfCarbohydrate() {
        return percentOfCarbohydrate;
    }
}
