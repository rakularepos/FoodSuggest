package org.example.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final String APP_KEY = "6830f031117f47f6bc9cdccbad1c4f94";
    private static final String APP_SECRET= "f7ed78ed0de14b1b9bd20d78febe3179";
    private static final String profile_auth_secret="61ecc8f641274dc0b6227f8d6a0ebd71";
    private static final String profile_auth_token="417679c219654613a4b1d8ce0c24905e";

    public static void main( String[] args ) throws Exception {
        System.out.println("Hello Food Suggest!\n Starting the jetty server on 8080 in MAIN");
        Server server = new Server(8080);

        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ctx.setContextPath("/");

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/fs/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages",
                "org.example.res");

        server.setHandler(ctx);
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}
