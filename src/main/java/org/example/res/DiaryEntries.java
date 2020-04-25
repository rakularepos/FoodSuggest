package org.example.res;

import com.google.gson.Gson;
import org.example.app.FSUtils;
import org.example.app.db.DBClient;
import org.example.beans.FoodDiaryEntry;
import org.example.beans.UserProfile;
import org.example.beans.request.CreateFoodDiaryEntryRequest;
import org.example.beans.response.GetFoodDiaryEntryResponse;
import org.example.fatsecret.FatSecretClient;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("diary")
public class DiaryEntries {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiary(
            @QueryParam("profileid") String profileId,
            @QueryParam("date") String date) {
        //List<GetFoodDiaryEntryResponse> diaryEntries;
        Map<String, List<GetFoodDiaryEntryResponse>> diaryEntries;
        try {
            LocalDate localDate = FSUtils.getLocalDateFromDateString(date);
            validateGetDiary(profileId, localDate);
            UserProfile profile = DBClient.getInstance().getProfile(profileId);

            JSONObject response = FatSecretClient.getInstance().getFoodDiaryEntriesForDate(profile, localDate);
            diaryEntries = getDiaryEntriesFromJsonResponse(response, profileId);
            //diaryEntries = DBClient.getInstance().getFoodDiaryEntriesByDate(profileId, FSUtils.getDaysSinceEpochAsString(date));
            Gson gson = new Gson();
            return Response.ok(gson.toJson(diaryEntries)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).
                    header("Access-Control-Allow-Origin", "*").build();
        }
    }

    private Map<String, List<GetFoodDiaryEntryResponse>> getDiaryEntriesFromJsonResponse(JSONObject response, String profileId) {
        Map<String, List<GetFoodDiaryEntryResponse>> diaryByMealType = new HashMap<>();
        //List<GetFoodDiaryEntryResponse> results = new ArrayList<>();
        JSONObject foodEntriesJson = response.getJSONObject("food_entries");
        Iterator foodEntriesIterator = foodEntriesJson.getJSONArray("food_entry").iterator();
        Gson gson = new Gson();
        while(foodEntriesIterator.hasNext()) {
            GetFoodDiaryEntryResponse entry = new GetFoodDiaryEntryResponse((JSONObject)foodEntriesIterator.next(), profileId);
            List<GetFoodDiaryEntryResponse> mapVals = diaryByMealType.getOrDefault(entry.getMealType(), new ArrayList<>());
            mapVals.add(entry);
            diaryByMealType.put(entry.getMealType(), mapVals);
            //results.add(entry);
        }
        //FoodDiaryEntry entry = new Gson().fromJson(foodEntriesJson.getJSONObject("food_entry").toString(), FoodDiaryEntry.class);
        //results.add(entry);
        //return results;
        return diaryByMealType;
    }

    private void validateGetDiary(String profileusername, LocalDate date) throws Exception {
        if (profileusername==null || profileusername.length()==0)
            throw new Exception("Invalid username");
        if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.now().minusDays(180)))
            throw new Exception("Invalid date");
    }

    @Path("/entry/{foodentryid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiaryEntry(@PathParam("foodentryid") String foodentryid,
                                  @QueryParam("profileid") String profileId) {
        try {
            if (foodentryid == null || foodentryid.length() == 0)
                return Response.status(Response.Status.BAD_REQUEST).
                        header("Access-Control-Allow-Origin", "*").build();

            GetFoodDiaryEntryResponse getFoodDiaryEntryResponse = getDiaryEntryResponse(foodentryid, profileId);
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(getFoodDiaryEntryResponse);
            return Response.ok(jsonResponse).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).
                    header("Access-Control-Allow-Origin", "*").build();
        }
    }

    private GetFoodDiaryEntryResponse getDiaryEntryResponse(String foodEntryId, String profileId) throws Exception {
        UserProfile profile = DBClient.getInstance().getProfile(profileId);
        JSONObject response = FatSecretClient.getInstance().getFoodDiaryEntriesByFoodEntryId(profile,foodEntryId);
        JSONObject foodEntry = response.getJSONObject("food_entries").getJSONObject("food_entry");
        return new GetFoodDiaryEntryResponse(foodEntry, profileId);
    }

    @Path("/createentry")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response createDiaryEntry(String body) {
        Gson gson = new Gson();
        CreateFoodDiaryEntryRequest createFoodDiaryEntryRequest = gson.fromJson(body, CreateFoodDiaryEntryRequest.class);
        System.out.println("Creating a new food diary entry:" +createFoodDiaryEntryRequest.toString());

        try {
            validateCreateDiaryRequest(createFoodDiaryEntryRequest);
            UserProfile profile =
                    DBClient.getInstance().getProfile(createFoodDiaryEntryRequest.getProfileId());
            JSONObject response =
                    FatSecretClient.getInstance().createFoodDiaryEntry(profile,
                            createFoodDiaryEntryRequest);
            FoodDiaryEntry foodDiaryEntry = new FoodDiaryEntry(createFoodDiaryEntryRequest,
                    response.getJSONObject("food_entry_id").getString("value"), "default_description");
            DBClient.getInstance().createDiaryEntry(foodDiaryEntry);
            System.out.println(foodDiaryEntry.toString());
            return Response.ok(gson.toJson(foodDiaryEntry)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).
                    header("Access-Control-Allow-Origin", "*").build();
        }
    }

    private void validateCreateDiaryRequest(CreateFoodDiaryEntryRequest createFoodDiaryEntryRequest) throws Exception {
        if (createFoodDiaryEntryRequest.getProfileId() == null ||
                createFoodDiaryEntryRequest.getProfileId().length() == 0)
            throw new Exception("Invalid profileId");
        if (createFoodDiaryEntryRequest.getFoodId() == 0)
            throw new Exception("Invalid foodId");
        if (createFoodDiaryEntryRequest.getFoodEntryName() == null ||
                createFoodDiaryEntryRequest.getFoodEntryName().length() == 0)
            throw new Exception("Invalid food entry name");
        if (createFoodDiaryEntryRequest.getServingId() == 0)
            throw new Exception("Invalid servingId");
        if (createFoodDiaryEntryRequest.getNumberOfUnits() == 0)
            throw new Exception("Invalid number of units");
        if (createFoodDiaryEntryRequest.getMealType() == null ||
                createFoodDiaryEntryRequest.getMealType().length()==0)
            throw new Exception("Invalid meal type");
        if (createFoodDiaryEntryRequest.getDate() == null || createFoodDiaryEntryRequest.getDate().length() == 0)
            throw new Exception("Invalid date");
    }
}