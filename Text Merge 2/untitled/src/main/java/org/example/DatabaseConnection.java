package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.net.URLEncoder;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class DatabaseConnection {

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection collection;
    public String InsertTextList(List<String> texts){
        CreateConnection();
        Document textDoc = new Document();

        for (int i = 0; i< texts.size(); i++) {
            textDoc.append("metin"+ (i+1) ,texts.get(i));
        }
        BsonValue id = collection.insertOne(textDoc).getInsertedId();
        CloseConnection();
        return id.asObjectId().getValue().toString(); // returns object id as String
    }
    public String InsertOneText(String text){
        CreateConnection();
        Document textDoc = new Document().append("birleştirilmiş metin",text);
        BsonValue id = collection.insertOne(textDoc).getInsertedId();
        CloseConnection();
        return id.asObjectId().getValue().toString(); // returns object id as String
    }
    // create the MongoClient when you're gonna use database
    private void CreateConnection(){
        // bağlanmazsa local databse i çalıştırmak için services'dan "MongoDB Database Server (MongoDB)" adlı servisi çalıştır
        client = MongoClients.create("mongodb://localhost:27017/");
        db = client.getDatabase("TextDatabase");
        collection = db.getCollection("Texts");
    }

    // Close the MongoClient when you're done
    private void CloseConnection(){
        client.close();
    }
}

