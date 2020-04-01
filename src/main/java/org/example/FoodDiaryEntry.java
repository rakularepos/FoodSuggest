package org.example;

import java.time.LocalDate;

public class FoodDiaryEntry {
    private long foodId;
    private String foodEntryName;
    private long servingId;
    private double numberOfUnits;
    private String mealType;
    private LocalDate date;

    public FoodDiaryEntry(long foodId, String foodEntryName,
                          long servingId, double numberOfUnits,
                          String mealType, LocalDate date) {
        this.foodId = foodId;
        this.foodEntryName = foodEntryName;
        this.servingId = servingId;
        this.numberOfUnits = numberOfUnits;
        this.mealType = mealType;
        this.date = date;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getFoodEntryName() {
        return foodEntryName;
    }

    public void setFoodEntryName(String foodEntryName) {
        this.foodEntryName = foodEntryName;
    }

    public long getServingId() {
        return servingId;
    }

    public void setServingId(long servingId) {
        this.servingId = servingId;
    }

    public double getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(double numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FoodDiaryEntry{" +
                "foodId=" + foodId +
                ", foodEntryName='" + foodEntryName + '\'' +
                ", servingId=" + servingId +
                ", numberOfUnits=" + numberOfUnits +
                ", mealType='" + mealType + '\'' +
                ", date=" + date.toString() +
                '}';
    }
}
