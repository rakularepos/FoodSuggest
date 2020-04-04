package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Hello world!
 *
 */
public class App {
    private static final String APP_KEY = "6830f031117f47f6bc9cdccbad1c4f94";
    private static final String APP_SECRET= "f7ed78ed0de14b1b9bd20d78febe3179";
    private static final String profile_auth_secret="61ecc8f641274dc0b6227f8d6a0ebd71";
    private static final String profile_auth_token="417679c219654613a4b1d8ce0c24905e";

    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello Food Suggest!\n Starting the jetty server on 8080" );
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(IntroServlet.class, "/intro/*");
        handler.addServletWithMapping(ProfilesServlet.class, "/profiles");

        // Add a single handler on context "/hello"
        /*ContextHandler context = new ContextHandler();
        context.setContextPath("/intro");
        context.setHandler(new App());*/

        // Can be accessed using http://localhost:8080/hello
        server.setHandler(handler);

        server.start();
        server.join();

        FatSecretClient fssClient = new FatSecretClient(APP_KEY, APP_SECRET);
        // Create a new profile.
        /*try {
            JSONObject response = fssClient.createProfile();
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Get Profile
        /*try {
            JSONObject response = fssClient.getProfile(profile_auth_token, profile_auth_secret);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Update Profile values
        /*UserProfile profile =
                new UserProfile(profile_auth_token, profile_auth_secret, "John Doe",
                100, 90, "Kg", 180, "Cm",
                TargetGoal.LOSE_WEIGHT);
        try {
            JSONObject response = fssClient.updateProfile(profile);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Get Profile
        /*try {
            JSONObject response = fssClient.getProfile(profile_auth_token, profile_auth_secret);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Create food diary entry
        /*try {
            LocalDate date = LocalDate.now();
            FoodDiaryEntry foodDiaryEntry = new FoodDiaryEntry(4384, "Plain French Toast",
                    16758, 1.000, "breakfast", date);
            JSONObject response = fssClient.createFoodDiaryEntry(profile, foodDiaryEntry);
            System.out.println(response.toString());
            System.out.println("Remaining calories for "+profile.getProfileUserName() +": "+
                    profile.getRemainingCalories(fssClient, date));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Get food diary entries by date
        /*try {
            JSONObject response = fssClient.getFoodDiaryEntriesForDate(profile,
                    LocalDate.of(2020, 03, 29));
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Get food diary entry by food entry Id
        /*try {
            JSONObject response = fssClient.getFoodDiaryEntriesByFoodId(profile, 6795563708L);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Delete food diary entry by Id
        /*try {
            JSONObject response = fssClient.deleteFoodDiaryEntry(profile, 6790542582L);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Get food diary entries by date
        /*try {
            JSONObject response = fssClient.getFoodDiaryEntriesForDate(profile,
                    LocalDate.of(2020, 03, 27));
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
