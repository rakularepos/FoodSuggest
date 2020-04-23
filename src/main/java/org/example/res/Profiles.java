package org.example.res;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.gson.Gson;
import org.example.app.db.DBClient;
import org.example.beans.UserProfile;
import org.example.beans.request.CreateProfile;
import org.example.beans.response.CreateProfileResponse;
import org.example.fatsecret.FatSecretClient;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringWriter;

@Path("profiles")
public class Profiles {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getProfiles() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/intro.mustache");
        StringWriter writer = new StringWriter();
        m.execute(writer, (Object)null);
        String html = writer.toString();
        //return html;
        return Response.ok(html).header("Access-Control-Allow-Origin", "*").build();
    }

    @Path("/getProfile/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@Context final ContainerRequestContext containerRequestContext,
                               @PathParam("id") int profileId) {
        System.out.println("Get profile req with id "+profileId);
        return Response.ok().header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @Path("/createProfilePage")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getCreateProfilePage() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/CreateProfile.mustache");
        StringWriter writer = new StringWriter();
        m.execute(writer, (Object)null);
        String html = writer.toString();
        //return html;
        return Response.ok(html).header("Access-Control-Allow-Origin", "*").build();
    }

    @Path("/createprofile")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response createProfile(String body) {
        Gson gson = new Gson();
        CreateProfile createProfileReq = gson.fromJson(body, CreateProfile.class);
        System.out.println("Creating a new profile:" +createProfileReq.toString());

        try {
            validateProfile(createProfileReq);
            UserProfile profile = createFSProfile(createProfileReq);
            DBClient.getInstance().createProfile(profile);
            CreateProfileResponse response = new CreateProfileResponse(profile.getProfileId());
            System.out.println(createProfileReq.toString());
            return Response.ok(gson.toJson(response)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).
                    header("Access-Control-Allow-Origin", "*").build();
        }
    }

    private UserProfile createFSProfile(CreateProfile profileReq) throws Exception {
        JSONObject response = FatSecretClient.getInstance().createProfile();
        System.out.println(response.toString());
        return new UserProfile(
                response.getJSONObject("profile").getString("auth_token"),
                response.getJSONObject("profile").getString("auth_secret"),
                profileReq.getProfileUserName(),
                profileReq.getCurrentWeight(),
                profileReq.getGoalWeight(),
                profileReq.getWeightUnit(),
                profileReq.getCurrentHeight(),
                profileReq.getHeightUnit());
    }

    private void validateProfile(CreateProfile profile) throws Exception {
        if (profile == null)
            throw new Exception("Profile is null!");
        if (profile.getProfileUserName() == null || profile.getProfileUserName().length()==0)
            throw new Exception("Invalid username");
    }
}