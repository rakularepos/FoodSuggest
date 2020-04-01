package org.example;

import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Request;
import org.json.JSONObject;

import java.time.LocalDate;

public class FatSecretClient extends FatsecretService {
    FatSecretRequestBuilder fssReqBuilder;
    FatSecretRequest fssRequest;

    private Request request;
    public FatSecretClient(String APP_KEY, String APP_SECRET) {
        super(APP_KEY, APP_SECRET);
        fssReqBuilder = new FatSecretRequestBuilder(APP_KEY, APP_SECRET);
        fssRequest = new FatSecretRequest(APP_KEY, APP_SECRET);
    }

    public JSONObject createProfile() throws Exception {
        String apiUrl = fssReqBuilder.buildProfileCreateUrl();
        System.out.println("Sending CREATE_PROFILE request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }

    public JSONObject getProfile(String oauthToken, String oauthSecret) throws Exception {
        String apiUrl = fssReqBuilder.buildProfileGetUrl(oauthToken, oauthSecret);
        System.out.println("Sending GET_PROFILE request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }

    public JSONObject updateProfile(UserProfile profile) throws Exception {
        String apiUrl = fssReqBuilder.buildProfileUpdateUrl(profile);
        System.out.println("Sending UPDATE_PROFILE request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }

    public JSONObject createFoodDiaryEntry(UserProfile profile,
                                           FoodDiaryEntry foodDiaryEntry) throws Exception {
        String apiUrl = fssReqBuilder.createFoodDiaryEntryUrl(profile, foodDiaryEntry);
        System.out.println("Sending CREATE_FOOD_DIARY_ENTRY request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }

    public JSONObject getFoodDiaryEntriesForDate(UserProfile profile, LocalDate date) throws Exception {
        long daysSinceEpoch = date.toEpochDay();
        String apiUrl = fssReqBuilder.getFoodDiaryEntriesForDateUrl(profile, daysSinceEpoch);
        System.out.println("Sending GET_FOOD_DIARY_ENTRY_BY_DATE request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }

    public JSONObject getFoodDiaryEntriesByFoodId(UserProfile profile, long foodEntryId) throws Exception {
        String apiUrl = fssReqBuilder.getFoodDiaryEntriesByFoodIdUrl(profile, foodEntryId);
        System.out.println("Sending GET_FOOD_DIARY_ENTRY_BY_FOODID request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }

    public JSONObject deleteFoodDiaryEntry(UserProfile profile,
                                           long foodEntryId) throws Exception {
        String apiUrl = fssReqBuilder.deleteFoodDiaryEntryUrl(profile, foodEntryId);
        System.out.println("Sending DELETE_FOOD_DIARY_ENTRY request to "+apiUrl);
        JSONObject jsonResponse = fssRequest.getJSONResponse(apiUrl);
        try {
            if (jsonResponse != null) {
                //JSONObject profile = jsonResponse.getJSONObject("food");
                return jsonResponse;
            }
        } catch (Exception var4) {
            System.out.println("Exception: " + var4.getMessage());
        }

        return null;
    }
}
