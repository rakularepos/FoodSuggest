package org.example;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Optional;

public class UserProfile {
    private String profileUserName;
    private double currentWeight;
    private double goalWeight;
    private String weightUnit;
    private double currentHeight;
    private String heightUnit;
    private String profileAuthSecret;
    private String profileAuthToken;
    private TargetGoal targetGoal;
    private DailyTarget dailyTarget;

    private String DEFAULT_PROFILE_USERNAME = "John Doe";
    private double DEFAULT_CURRENT_WEIGHT = 0.0;
    private double DEFAULT_GOAL_WEIGHT = 0.0;
    private String DEFAULT_WEIGHT_UNIT = "Kg";
    private double DEFAULT_CURRENT_HEIGHT = 0.0;
    private String DEFAULT_HEIGHT_UNIT = "Cm";

    public UserProfile(String profile_auth_token, String profile_auth_secret) {
        new UserProfile(profile_auth_token, profile_auth_secret, DEFAULT_PROFILE_USERNAME,
                        DEFAULT_CURRENT_WEIGHT, DEFAULT_GOAL_WEIGHT, DEFAULT_WEIGHT_UNIT,
                        DEFAULT_CURRENT_HEIGHT, DEFAULT_HEIGHT_UNIT, TargetGoal.NONE);
    }

    public UserProfile(String profileAuthToken, String profileAuthSecret, String profileUserName,
                       double currentWeight, double goalWeight, String weightUnit,
                       double currentHeight, String heightUnit, TargetGoal targetGoal) {
        this.profileAuthToken = profileAuthToken;
        this.profileAuthSecret = profileAuthSecret;
        this.profileUserName = profileUserName;
        this.currentWeight = currentWeight;
        this.goalWeight = goalWeight;
        this.weightUnit = weightUnit;
        this.currentHeight = currentHeight;
        this.heightUnit = heightUnit;
        this.targetGoal = targetGoal;
        this.dailyTarget = currentWeight==DEFAULT_CURRENT_WEIGHT?
                new DailyTarget() : calculateDailyTarget();
    }

    private DailyTarget calculateDailyTarget() {
        return new DailyTarget(2000, 100, 100, 100);
    }

    public String getRemainingCalories(FatSecretClient fssClient, LocalDate date) {
        try {
            JSONObject response = fssClient.getFoodDiaryEntriesForDate(this, date);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "2000";
    }

    public String getProfileUserName() {
        return profileUserName;
    }

    public void setProfileUserName(String profileUserName) {
        this.profileUserName = profileUserName;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public double getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(double currentHeight) {
        this.currentHeight = currentHeight;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public String getProfileAuthSecret() {
        return profileAuthSecret;
    }

    public String getProfileAuthToken() {
        return profileAuthToken;
    }

    public TargetGoal getTargetGoal() {
        return targetGoal;
    }

    public void setTargetGoal(TargetGoal targetGoal) {
        this.targetGoal = targetGoal;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileUserName='" + profileUserName + '\'' +
                ", currentWeight=" + currentWeight +
                ", goalWeight=" + goalWeight +
                ", weightUnit='" + weightUnit + '\'' +
                ", currentHeight=" + currentHeight +
                ", heightUnit='" + heightUnit + '\'' +
                ", profileAuthSecret='" + profileAuthSecret + '\'' +
                ", profileAuthToken='" + profileAuthToken + '\'' +
                ", targetGoal=" + targetGoal +
                ", dailyTarget=" + dailyTarget +
                '}';
    }
}
