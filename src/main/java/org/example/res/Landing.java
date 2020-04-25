package org.example.res;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringWriter;

@Path("/")
public class Landing {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHomePage() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/Landing.mustache");
        StringWriter writer = new StringWriter();
        m.execute(writer, (Object)null);
        String html = writer.toString();
        //return html;
        return Response.ok(html).header("Access-Control-Allow-Origin", "*").build();
    }
}