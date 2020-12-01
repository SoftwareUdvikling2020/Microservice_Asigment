package dk.si.demo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class AutoDBFiller {
    public static void main(String[] args) {
        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "188.166.16.16" , 27017 );
        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("admin", "inventory",
                "secure".toCharArray());
        System.out.println("Connected to the database successfully");

        //Accessing the database
        MongoDatabase database = mongo.getDatabase("myDb");

        // Retrieving a collection
        MongoCollection<Document> carCollection = database.getCollection("car");
        MongoCollection<Document> userCollection = database.getCollection("user");
        MongoCollection<Document> ratingCollection = database.getCollection("rating");
        // r(Long id, String name, List<CarRating> carsRated) {
        long carId = 1;


        Document ratingDoc1 = new Document("_id", new ObjectId());
        ratingDoc1.append("ratingId",11).append("carId",carId).append("rating",5);

        Document ratingDoc2 = new Document("_id", new ObjectId());
        ratingDoc2.append("ratingId",12).append("carId",carId).append("rating",4);

        Document ratingDoc3 = new Document("_id", new ObjectId());
        ratingDoc3.append("ratingId",13).append("carId",carId).append("rating",3);

        List<Document> ratins = new ArrayList<>();
        ratins.add(ratingDoc1);
        ratins.add(ratingDoc2);
        ratins.add(ratingDoc3);

        Document userDoc1 = new Document("_id", new ObjectId());
        userDoc1.append("userId",1).append("name","karsten").append("ratings",ratins);

        Document carDoc1 = new Document("_id", new ObjectId());
        carDoc1.append("carId", carId).append("brand","Volvo").append("year",2000).append("km",1234).append("ratings",ratins);

        ratingCollection.insertMany(ratins);
        carCollection.insertOne(carDoc1);
        userCollection.insertOne(userDoc1);

        System.out.println("Document inserted successfully");
    }
}
