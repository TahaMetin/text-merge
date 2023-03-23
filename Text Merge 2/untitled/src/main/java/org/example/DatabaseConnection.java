package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.URLEncoder;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class DatabaseConnection {

    public static void main(String[] args) {

        // bağlanmazsa local databse i çalıştırmak için services'dan "MongoDB Database Server (MongoDB)" adlı servisi çalıştır
        MongoClient client = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase db = client.getDatabase("TextDatabase");
        MongoCollection collection = db.getCollection("Texts");

        Document sampleDoc = new Document().append("name","uzay");
        collection.insertOne(sampleDoc);

        // Close the MongoClient when you're done
        client.close();


    }
}

