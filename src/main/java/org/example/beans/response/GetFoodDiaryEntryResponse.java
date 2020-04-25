package org.example.beans.response;

import com.google.gson.Gson;
import org.example.beans.FoodDiaryEntry;
import org.example.beans.Nutrient;
import org.json.JSONObject;

public class GetFoodDiaryEntryResponse {
    FoodDiaryEntry foodDiaryEntry;
    Nutrient nutrients;

    public GetFoodDiaryEntryResponse(JSONObject foodEntry, String profileId)
    {
        this.foodDiaryEntry = new FoodDiaryEntry(
                foodEntry.getString("food_entry_id"),
                foodEntry.getLong("food_id"),
                foodEntry.getString("food_entry_name"),
                foodEntry.getLong("serving_id"),
                foodEntry.getDouble("number_of_units"),
                foodEntry.getString("meal"),
                foodEntry.getLong("date_int"),
                profileId,
                foodEntry.getString("food_entry_description")
        );

        /*this.nutrients = new Nutrient.NutrientBuilder()
                        .setCalories(foodEntry.getDouble("calories"))
                        .setCarbohydrate(foodEntry.getDouble("carbohydrate"))
                        .setProtein(foodEntry.getDouble("protein"))
                        .setFat(foodEntry.getDouble("fat"))
                        .setSaturated_fat(foodEntry.getDouble("saturated_fat"))
                        .setPolyunsaturated_fat(foodEntry.getDouble("polyunsaturated_fat"))
                        .setMonounsaturated_fat(foodEntry.getDouble("monounsaturated_fat"))
                        .setTrans_fat(foodEntry.getDouble("trans_fat"))
                        .setCholesterol(foodEntry.getDouble("cholesterol"))
                        .setSodium(foodEntry.getDouble("sodium"))
                        .setPotassium(foodEntry.getDouble("potassium"))
                        .setFiber(foodEntry.getDouble("fiber"))
                        .setSugar(foodEntry.getDouble("sugar"))
                        .setVitamin_a(foodEntry.getDouble("vitamin_a"))
                        .setVitamin_c(foodEntry.getDouble("vitamin_c"))
                        .setCalcium(foodEntry.getDouble("calcium"))
                        .setIron(foodEntry.getDouble("iron"))
                        .build();*/
        Gson gson = new Gson();
        this.nutrients = gson.fromJson(foodEntry.toString(), Nutrient.class);
    }

    public String getMealType() {
        return this.foodDiaryEntry.getMealType();
    }
}
