package org.example.beans;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.Indexes;
import org.example.fatsecret.FatSecretClient;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.UUID;

@Entity("profiles")
@Indexes(
        @Index(value = "profileId", fields = @Field("profileId"))
)
public class UserProfile {
    @Id
    private String profileId;
    private String profileUserName;
    private double currentWeight;
    private double goalWeight;
    private String weightUnit;
    private double currentHeight;
    private String heightUnit;
    private TargetGoal targetGoal;
    @Embedded
    private DailyTarget dailyTarget;

    private String profileAuthSecret;
    private String profileAuthToken;

    public UserProfile() {
    }

    public UserProfile(String profileAuthToken, String profileAuthSecret, String profileUserName,
                       double currentWeight, double goalWeight, String weightUnit,
                       double currentHeight, String heightUnit) {
        this.profileAuthToken = profileAuthToken;
        this.profileAuthSecret = profileAuthSecret;
        this.profileUserName = profileUserName;
        this.currentWeight = currentWeight;
        this.goalWeight = goalWeight;
        this.weightUnit = weightUnit;
        this.currentHeight = currentHeight;
        this.heightUnit = heightUnit;
        this.targetGoal = calculateTargetGoal(currentWeight, goalWeight);
        this.dailyTarget = currentWeight==0?
                new DailyTarget() : calculateDailyTarget();
        this.profileId = UUID.randomUUID().toString();
    }

    private TargetGoal calculateTargetGoal(double currentWeight, double goalWeight) {
        if (currentWeight > goalWeight)
            return TargetGoal.LOSE_WEIGHT;
        else if (currentWeight < goalWeight)
            return TargetGoal.GAIN_WEIGHT;
        else
            return TargetGoal.MAINTAIN_WEIGHT;
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

    public void setProfileAuthSecret(String profileAuthSecret) {
        this.profileAuthSecret = profileAuthSecret;
    }

    public void setProfileAuthToken(String profileAuthToken) {
        this.profileAuthToken = profileAuthToken;
    }

    public DailyTarget getDailyTarget() {
        return dailyTarget;
    }

    public void setDailyTarget(DailyTarget dailyTarget) {
        this.dailyTarget = dailyTarget;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileId=" + profileId +
                ", profileUserName='" + profileUserName + '\'' +
                ", currentWeight=" + currentWeight +
                ", goalWeight=" + goalWeight +
                ", weightUnit='" + weightUnit + '\'' +
                ", currentHeight=" + currentHeight +
                ", heightUnit='" + heightUnit + '\'' +
                ", targetGoal=" + targetGoal +
                ", dailyTarget=" + dailyTarget +
                ", profileAuthSecret='" + profileAuthSecret + '\'' +
                ", profileAuthToken='" + profileAuthToken + '\'' +
                '}';
    }
}
