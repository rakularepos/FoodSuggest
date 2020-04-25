package org.example.beans;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import org.example.app.FSUtils;
import org.example.beans.request.CreateFoodDiaryEntryRequest;

@Entity("diaryentries")
public class FoodDiaryEntry {
    @Id
    private String foodEntryId;
    private long foodId;
    private String foodEntryName;
    private long servingId;
    private double numberOfUnits;
    private String mealType;
    @Indexed
    private long date;
    @Indexed
    private String profileId;
    private String foodEntryDesc;

    public FoodDiaryEntry() {
    }

    public FoodDiaryEntry(String foodEntryId, long foodId, String foodEntryName,
                          long servingId, double numberOfUnits,
                          String mealType, long date, String profileId, String foodEntryDesc) {
        this.foodEntryId = foodEntryId;
        this.foodId = foodId;
        this.foodEntryName = foodEntryName;
        this.servingId = servingId;
        this.numberOfUnits = numberOfUnits;
        this.mealType = mealType;
        this.date = date;
        this.profileId = profileId;
        this.foodEntryDesc = foodEntryDesc;
    }

    public FoodDiaryEntry(CreateFoodDiaryEntryRequest req, String foodEntryId, String foodEntryDesc) {
        this(foodEntryId, req.getFoodId(), req.getFoodEntryName(), req.getServingId(),
                req.getNumberOfUnits(), req.getMealType(),
                FSUtils.getDaysSinceEpochAsString(req.getDate()),
                req.getProfileId(), foodEntryDesc);
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getFoodEntryId() {
        return foodEntryId;
    }

    public void setFoodEntryId(String foodEntryId) {
        this.foodEntryId = foodEntryId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getFoodEntryDesc() {
        return foodEntryDesc;
    }

    public FoodDiaryEntry setFoodEntryDesc(String foodEntryDesc) {
        this.foodEntryDesc = foodEntryDesc;
        return this;
    }

    @Override
    public String toString() {
        return "FoodDiaryEntry{" +
                "foodEntryId='" + foodEntryId + '\'' +
                ", foodId=" + foodId +
                ", foodEntryName='" + foodEntryName + '\'' +
                ", servingId=" + servingId +
                ", numberOfUnits=" + numberOfUnits +
                ", mealType='" + mealType + '\'' +
                ", date=" + date +
                ", profileId='" + profileId + '\'' +
                ", foodEntryDesc='" + foodEntryDesc + '\'' +
                '}';
    }
}
