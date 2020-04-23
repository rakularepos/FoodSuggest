package org.example.beans;

import org.bson.Document;

import javax.ws.rs.DefaultValue;

public class DailyTarget {
    @DefaultValue("0.0")
    private double calories;
    @DefaultValue("0.0")
    private double carbs;
    @DefaultValue("0.0")
    private double fats;
    @DefaultValue("0.0")
    private double protein;

    public DailyTarget(double calories, double carbs, double fats, double protein) {
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
    }

    public DailyTarget() {
        this.calories = 0;
        this.carbs = 0;
        this.fats = 0;
        this.protein = 0;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "DailyTarget{" +
                "calories=" + calories +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", protein=" + protein +
                '}';
    }

    public final Document toDocument() {
        return new Document("calories", calories)
                .append("carbs", carbs)
                .append("fats", fats)
                .append("protein", protein);
    }
}
