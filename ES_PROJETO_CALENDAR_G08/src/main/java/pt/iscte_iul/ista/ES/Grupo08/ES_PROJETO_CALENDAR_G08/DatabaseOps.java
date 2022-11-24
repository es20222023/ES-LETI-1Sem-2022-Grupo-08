package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import java.io.File;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class DatabaseOps

{
	String collectionName;
	
	
	DatabaseOps (){
		
	}
	
	DatabaseOps (String user){
		collectionName = user;
	}
	
	
	
	
	public static void main( String[] args )
	{
		DatabaseOps DB = new DatabaseOps();
		DB.DB_Ops();
	}
	
	public void DB_Ops() {
		try {
			MongoClient mongodb = new MongoClient("localhost", 27017);
			MongoDatabase database = mongodb.getDatabase("ProjetoES_DB");
			MongoCollection<Document> coll = database.getCollection("EventosPedro");
			Document doc = new Document();
			doc.put("Summary", "EPPDS");
			doc.put("DATE", "20220912");
			
	
			coll.insertOne(doc);
			mongodb.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
