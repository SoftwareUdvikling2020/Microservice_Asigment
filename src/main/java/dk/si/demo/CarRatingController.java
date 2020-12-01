package dk.si.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarRatingController {


    //Car has id 1
    @GetMapping("/car/{id}")
    public ResponseEntity getCarRating(@PathVariable(value = "id") int id) {
        MongoClient mongo = new MongoClient("188.166.16.16", 27017);
        MongoDatabase database = mongo.getDatabase("myDb");
        MongoCollection<Document> collection = database.getCollection("car");

        Document myDoc = collection.find(eq("carId", id)).first();
        List<Document> ratings = (List<Document>) myDoc.get("ratings");
        IntSummaryStatistics stats = ratings.stream().mapToInt(rating -> (int) rating.get("rating")).summaryStatistics();
        return ResponseEntity.ok().body(stats);
    }

    //User has id 1
    @GetMapping("/user/{id}")
    public ResponseEntity getUserCarRating(@PathVariable(value = "id") int id) {
        MongoClient mongo = new MongoClient("188.166.16.16", 27017);
        MongoDatabase database = mongo.getDatabase("myDb");
        MongoCollection<Document> collection = database.getCollection("user");

        Document myDoc = collection.find(eq("userId", id)).first();
        List<Document> ratings = (List<Document>) myDoc.get("ratings");
        return ResponseEntity.ok().body(ratings);
    }

}

