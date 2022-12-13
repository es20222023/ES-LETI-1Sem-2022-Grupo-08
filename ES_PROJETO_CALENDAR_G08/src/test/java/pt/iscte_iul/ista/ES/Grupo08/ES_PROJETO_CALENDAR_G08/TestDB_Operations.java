package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;


public class TestDB_Operations {

	DB_Operations entry;
	MongoClient mongoClient;
	MongoDatabase database;

	@Before
	public void setup() {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://projetoesgrupo08:ProjetoESGrupo08@cluster0.qgzbwsz.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		mongoClient = MongoClients.create(settings);
		entry = new DB_Operations();
		database = mongoClient.getDatabase("ProjetoES");
		if(!database.listCollectionNames().into(new ArrayList<Object>()).contains("Eventos_pmaal1"))
			entry.icsToDB("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testIcsToDB() {
		entry.icsToDB("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");
		database = mongoClient.getDatabase("ProjetoES");
		assertTrue(database.listCollectionNames().into(new ArrayList()).contains("Eventos_pmaal1"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testClearDB() {
		database = mongoClient.getDatabase("ProjetoES");
		if(!database.listCollectionNames().into(new ArrayList()).contains("Eventos_pmaal1"))
			entry.icsToDB("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");
		boolean exists = false;
		entry.clearDB();
		MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
		while(dbsCursor.hasNext()) {
			if(dbsCursor.next().equals("ProjetoES"))
				exists = true;
		}
		assertFalse(exists);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testReadFromDB() {
		database = mongoClient.getDatabase("ProjetoES");
		if(!database.listCollectionNames().into(new ArrayList()).contains("Eventos_pmaal1"))
			entry.icsToDB("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");

		JSONArray events = entry.readFromDB("pmaal1");
		JSONObject first = (JSONObject) events.get(0);
		ArrayList<String> list = new ArrayList<String>(first.keySet());
		String firstKey = list.get(0);
		String secondKey = list.get(1);
		String thirdKey = list.get(2);
		String fourthKey = list.get(3);

		String expectedFirstKey = "SUMMARY";
		String expectedSecondKey = "START";
		String expectedThirdKey = "END";
		String expectedFourthKey = "_id";

		assertEquals(firstKey, expectedFirstKey);
		assertEquals(secondKey, expectedSecondKey);
		assertEquals(thirdKey,expectedThirdKey);
		assertEquals(fourthKey, expectedFourthKey);

		JSONObject second = (JSONObject) events.get(1);

		ArrayList<String> list2 = new ArrayList<String>(second.keySet());
		String firstKey2 = list2.get(0);
		String secondKey2 = list2.get(1);
		String thirdKey2 = list2.get(2);
		String fourthKey2 = list2.get(3);

		String expectedFirstKey2 = "SUMMARY";
		String expectedSecondKey2 = "START";
		String expectedThirdKey2 = "END";
		String expectedFourthKey2 = "_id";

		assertEquals(firstKey2, expectedFirstKey2);
		assertEquals(secondKey2, expectedSecondKey2);
		assertEquals(thirdKey2,expectedThirdKey2);
		assertEquals(fourthKey2, expectedFourthKey2);

	}









	@After
	public void closeClientConnection() {
		mongoClient.close();
	}




}
