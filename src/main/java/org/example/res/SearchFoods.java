package org.example.res;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.google.gson.Gson;
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
import java.util.List;

@Path("foods")
public class SearchFoods {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFoods(
            @QueryParam("query") String query,
            @QueryParam("maxresults") long maxResults,
            @QueryParam("pagenumber") int pageNumber) {
        List<FoodDiaryEntry> diaryEntries;
        try {
            validateGetFoods(query);
            com.fatsecret.platform.services.Response<CompactFood> response = FatSecretClient.getInstance().searchFoods(query, pageNumber);
            Gson gson = new Gson();
            return Response.ok(gson.toJson(response)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).
                    header("Access-Control-Allow-Origin", "*").build();
        }
    }

    private void validateGetFoods(String query) throws Exception {
        if (query==null || query.length()==0)
            throw new Exception("Invalid query string");
    }

    @Path("/{foodid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFood(@PathParam("foodid") long foodId) {
        try {
            Food food = FatSecretClient.getInstance().getFood(foodId);
            if (food == null)
                return Response.ok("").
                        header("Access-Control-Allow-Origin", "*").build();
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(food);
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