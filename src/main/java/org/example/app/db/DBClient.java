package org.example.app.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import org.example.beans.FoodDiaryEntry;
import org.example.beans.UserProfile;

public class DBClient {
    private static DBClient INSTANCE = null;
    final Datastore datastore;
    private static final String DB_NAME="userDB";

    private DBClient()
    {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        Morphia morphia = new Morphia();
        morphia.mapPackage("org.example.beans");

        datastore = morphia.createDatastore(mongoClient, DB_NAME);
        datastore.ensureIndexes();
    }

    public static DBClient getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new DBClient();
        return INSTANCE;
    }

    public void createProfile(UserProfile profile) {
        datastore.save(profile);
    }

    public UserProfile getProfile(String profileUserName) throws Exception {
        Query<UserProfile> query = datastore.createQuery(UserProfile.class);
        query.criteria("_id").equal(profileUserName);
        UserProfile profile = query.first();
        if (profile == null)
            throw new Exception("No profile exists with this username "+profileUserName);

        return profile;
    }

    public void createDiaryEntry(FoodDiaryEntry foodDiaryEntry) {
        datastore.save(foodDiaryEntry);
    }

    public FoodDiaryEntry getFoodDiaryEntry(String foodEntryId) throws Exception {
        Query<FoodDiaryEntry> query = datastore.createQuery(FoodDiaryEntry.class);
        query.criteria("_id").equal(foodEntryId);
        FoodDiaryEntry foodDiaryEntry = query.first();
        if (foodDiaryEntry == null)
            throw new Exception("No diary entry exists with this id "+foodEntryId);

        return foodDiaryEntry;
    }
}
