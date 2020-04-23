package org.example.beans.request;

import javax.ws.rs.DefaultValue;

public class CreateProfile {
    @DefaultValue("John Doe")
    private String profileUserName;
    @DefaultValue("0.0")
    private double currentWeight;
    @DefaultValue("0.0")
    private double goalWeight;
    @DefaultValue("Kg")
    private String weightUnit;
    @DefaultValue("10.0")
    private double currentHeight;
    @DefaultValue("Cm")
    private String heightUnit;

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

    @Override
    public String toString() {
        return "CreateProfile{" +
                "profileUserName='" + profileUserName + '\'' +
                ", currentWeight=" + currentWeight +
                ", goalWeight=" + goalWeight +
                ", weightUnit='" + weightUnit + '\'' +
                ", currentHeight=" + currentHeight +
                ", heightUnit='" + heightUnit + '\'' +
                '}';
    }
}
