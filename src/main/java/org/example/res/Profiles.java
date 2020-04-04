package org.example.res;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.gson.Gson;
import org.example.UserProfile;
import org.example.frontend.templateClasses.Intro;

import javax.ws.rs.GET;
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

    private static final String profile_auth_secret="61ecc8f641274dc0b6227f8d6a0ebd71";
    private static final String profile_auth_token="417679c219654613a4b1d8ce0c24905e";

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getProfiles() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/intro.mustache");
        StringWriter writer = new StringWriter();
        m.execute(writer, new Intro("Welcome to Food Suggest!!!"));
        String html = writer.toString();
        //return html;
        return Response.ok(html).header("Access-Control-Allow-Origin", "*").build();
    }

    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@Context final ContainerRequestContext containerRequestContext,
                               @PathParam("id") int profileId) {
        System.out.println("Returning Profiles with id "+profileId);
        Gson gson = new Gson();
        String jsonString = gson.toJson(new UserProfile(profile_auth_token, profile_auth_secret));
        System.out.println(jsonString);
        return Response.ok().header("Access-Control-Allow-Origin", "*")
                .entity(jsonString)
                .build();
    }
}