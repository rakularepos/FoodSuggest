package org.example.beans.request;

public class CreateFoodDiaryEntryRequest {
    private long foodId;
    private String foodEntryName;
    private long servingId;
    private double numberOfUnits;
    private String mealType;
    private String date;
    private String profileId;

    public CreateFoodDiaryEntryRequest(String profileId, long foodId, String foodEntryName,
                                       long servingId, double numberOfUnits,
                                       String mealType, String date) throws Exception {
        this.profileId = profileId;
        this.foodId = foodId;
        this.foodEntryName = foodEntryName;
        this.servingId = servingId;
        this.numberOfUnits = numberOfUnits;
        this.mealType = mealType;
        this.date = date;;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "CreateFoodDiaryEntryRequest{" +
                "foodId=" + foodId +
                ", foodEntryName='" + foodEntryName + '\'' +
                ", servingId=" + servingId +
                ", numberOfUnits=" + numberOfUnits +
                ", mealType='" + mealType + '\'' +
                ", date=" + date +
                ", profileId='" + profileId + '\'' +
                '}';
    }


}
