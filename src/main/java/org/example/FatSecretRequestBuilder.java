package org.example;

import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Base64;
import com.fatsecret.platform.services.RequestBuilder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FatSecretRequestBuilder extends RequestBuilder {
    private String APP_SECRET_LOCAL;
    private String APP_KEY_LOCAL;
    public FatSecretRequestBuilder(String APP_KEY, String APP_SECRET) {
        super(APP_KEY, APP_SECRET);
        this.APP_KEY_LOCAL = APP_KEY;
        this.APP_SECRET_LOCAL = APP_SECRET;
    }

    public String buildProfileCreateUrl() throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=profile.create");
        params.add("oauth_signature=" + this.sign("GET", "https://platform.fatsecret.com/rest/server.api",
                                                         (String[])params.toArray(template)));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    public String buildProfileGetUrl(String oauthToken, String oauthSecret) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=profile.get");
        params.add("oauth_token="+oauthToken);
        params.add("oauth_signature=" + this.signDelegated("GET", "https://platform.fatsecret.com/rest/server.api",
                (String[])params.toArray(template), oauthSecret));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    public String buildProfileUpdateUrl(UserProfile profile) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=weight.update");
        params.add("oauth_token="+profile.getProfileAuthToken());

        params.add("current_weight_kg="+profile.getCurrentWeight());
        params.add("goal_weight_kg="+profile.getGoalWeight());
        params.add("weight_type="+profile.getWeightUnit());

        params.add("current_height_cm="+profile.getCurrentHeight());
        params.add("height_type="+profile.getHeightUnit());

        params.add("oauth_signature=" + this.signDelegated("GET", "https://platform.fatsecret.com/rest/server.api",
                (String[])params.toArray(template), profile.getProfileAuthSecret()));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    public String createFoodDiaryEntryUrl(UserProfile profile,
                                          FoodDiaryEntry foodDiaryEntry) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=food_entry.create");
        params.add("oauth_token="+profile.getProfileAuthToken());

        params.add("food_id="+foodDiaryEntry.getFoodId());
        params.add("food_entry_name="+this.encode(foodDiaryEntry.getFoodEntryName()));
        params.add("serving_id="+foodDiaryEntry.getServingId());
        params.add("number_of_units="+foodDiaryEntry.getNumberOfUnits());
        params.add("meal="+this.encode(foodDiaryEntry.getMealType()));
        params.add("date="+foodDiaryEntry.getDate().toEpochDay());

        params.add("oauth_signature=" + this.signDelegated("GET", "https://platform.fatsecret.com/rest/server.api",
                (String[])params.toArray(template), profile.getProfileAuthSecret()));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    public String getFoodDiaryEntriesForDateUrl(UserProfile profile, long daysSinceEpoch) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=food_entries.get");
        params.add("oauth_token="+profile.getProfileAuthToken());
        params.add("date="+daysSinceEpoch);
        params.add("oauth_signature=" + this.signDelegated("GET", "https://platform.fatsecret.com/rest/server.api",
                (String[])params.toArray(template), profile.getProfileAuthSecret()));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    public String getFoodDiaryEntriesByFoodIdUrl(UserProfile profile, long foodEntryId ) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=food_entries.get");
        params.add("oauth_token="+profile.getProfileAuthToken());
        params.add("food_entry_id="+foodEntryId);
        params.add("oauth_signature=" + this.signDelegated("GET", "https://platform.fatsecret.com/rest/server.api",
                (String[])params.toArray(template), profile.getProfileAuthSecret()));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    public String deleteFoodDiaryEntryUrl(UserProfile profile,
                                          long foodEntryId) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(this.generateOauthParams()));
        String[] template = new String[1];
        params.add("method=food_entry.delete");
        params.add("oauth_token="+profile.getProfileAuthToken());
        params.add("food_entry_id="+foodEntryId);

        params.add("oauth_signature=" + this.signDelegated("GET", "https://platform.fatsecret.com/rest/server.api",
                (String[])params.toArray(template), profile.getProfileAuthSecret()));
        return "https://platform.fatsecret.com/rest/server.api?" + this.paramify((String[])params.toArray(template));
    }

    private String signDelegated(String method, String uri, String[] params, String oauthSecret) throws UnsupportedEncodingException {
        String encodedURI = this.encode(uri);
        String encodedParams = this.encode(this.paramify(params));
        String[] p = new String[]{method, encodedURI, encodedParams};
        String text = this.join(p, "&");
        String key = this.APP_SECRET_LOCAL + "&" + oauthSecret;
        SecretKey sk = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        String sign = "";

        try {
            Mac m = Mac.getInstance("HmacSHA1");
            m.init(sk);
            sign = this.encode((new String(Base64.encode(m.doFinal(text.getBytes()), 0))).trim());
        } catch (NoSuchAlgorithmException var12) {
            System.out.println("NoSuchAlgorithmException: " + var12.getMessage());
        } catch (InvalidKeyException var13) {
            System.out.println("InvalidKeyException: " + var13.getMessage());
        }

        return sign;
    }
}
