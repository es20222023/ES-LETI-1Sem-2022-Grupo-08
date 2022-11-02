package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.mongodb.*;

import com.mongodb.client.MongoDatabase;


public class TestDB {

	MongoClient mongodb;
	MongoDatabase database;
	public MongoClient getMongoClient() {
		MongoClient mongodb = new MongoClient("localhost", 27017);
		return mongodb;
	}

	@Before
	public void createMongoClient() {
		mongodb = getMongoClient();
		database = mongodb.getDatabase("ProjetoES_DB");
	}


	
	
	@Test
	public void testDropCollections() {
		String collectionName = "EventosPedro";

		if (database.getCollection(collectionName) != null) {
			database.getCollection(collectionName).drop();
			@SuppressWarnings("rawtypes")
			boolean collectionExists = database.listCollectionNames()
					.into(new ArrayList()).contains(collectionName);
			assertFalse(collectionExists);

		}
		
	}
	
	
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void checkColletionExistence(){
		
		
		String collectionName = "EventosPedro";
		String collectionName2 = "eventospedro";
		
		database.createCollection(collectionName);
		
		@SuppressWarnings("rawtypes")
		boolean collectionExists = database.listCollectionNames()
				.into(new ArrayList()).contains(collectionName);
		
		assertTrue(collectionExists);
		
		//Test for case sensitiveness of collectionNames
		@SuppressWarnings("rawtypes")
		boolean collectionExists2 = database.listCollectionNames()
				.into(new ArrayList()).contains(collectionName2);
		
		assertFalse(collectionExists2);
		
	}
	
	
	@After
	public void closeClientConnection() {
		mongodb.close();
	}
	



}
