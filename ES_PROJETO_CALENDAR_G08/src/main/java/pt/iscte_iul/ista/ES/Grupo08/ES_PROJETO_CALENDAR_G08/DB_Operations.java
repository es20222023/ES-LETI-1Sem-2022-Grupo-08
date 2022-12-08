package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import com.mongodb.ConnectionString;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Class with the MongoDB database operations.
 * @author Pedro Luis
 * @version 08/12/2022
 *
 */
public class DB_Operations {

	String username;

	/**
	 * Constructor
	 */
	public DB_Operations() {
		username = "";
	}

	/**
	 * Main method for small tests.
	 * @param args Main method arguments.
	 */
	public static void main(String[] args) {
		DB_Operations entry = new DB_Operations();
		entry.icsToDB("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");
		JSONArray output = entry.readFromDB(entry.username);
		System.out.println(output);
		System.out.println(output.size());
		//entry.clearDB();

	}

	/**
	 * Method to send Calendar info directly from Fenix URL into MongoDB database.
	 * @param url_str String with the Fenix calendar URL 
	 */
	@SuppressWarnings("unchecked")
	public void icsToDB(String url_str) {
		try {
			URL url = new URL(replace(url_str));
			username = getUsername(url_str);

			Scanner s = new Scanner(url.openStream());

			try {
				ConnectionString connectionString = new ConnectionString("mongodb+srv://projetoesgrupo08:ProjetoESGrupo08@cluster0.qgzbwsz.mongodb.net/?retryWrites=true&w=majority");
				MongoClientSettings settings = MongoClientSettings.builder()
						.applyConnectionString(connectionString)
						.build();
				MongoClient mongoClient = MongoClients.create(settings);
				MongoDatabase database = mongoClient.getDatabase("ProjetoES");
				String collectionName = "Eventos_"+username;
				MongoCollection<Document> coll = database.getCollection(collectionName);
				
				@SuppressWarnings("rawtypes")
				boolean collectionExists = database.listCollectionNames()
						  .into(new ArrayList()).contains(collectionName);
				if (collectionExists)
					dropCollection(coll);

				String line = s.nextLine();
				while (s.hasNext()) {
					if(line.contains("DTSTART")) {
						Document doc = new Document();

						doc.put("START", StringUtils.substringAfter(line, ":"));
						line = s.nextLine();
						doc.put("END", StringUtils.substringAfter(line, ":"));
						line = s.nextLine();
						doc.put("SUMMARY", StringUtils.substringAfter(line, ":"));

						//Creates an event object that contains de json object that contains the details

						coll.insertOne(doc);


						line = s.nextLine();  

					} else line = s.nextLine();

				}


			} 
			catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Method to get the Calendar info from MongoDB given an username.
	 * @param username Username to access the right database collection.
	 * @return JSONArray with JSON objects that will be used to generate an Array with CalendarEvents in EventsFromDBToCalendar class.
	 */
	
	@SuppressWarnings("unchecked")
	public JSONArray readFromDB(String username) {
		JSONArray calendar = new JSONArray();
		JSONParser parser = new JSONParser();  

		try {
			ConnectionString connectionString = new ConnectionString("mongodb+srv://projetoesgrupo08:ProjetoESGrupo08@cluster0.qgzbwsz.mongodb.net/?retryWrites=true&w=majority");
			MongoClientSettings settings = MongoClientSettings.builder()
					.applyConnectionString(connectionString)
					.build();
			MongoClient mongoClient = MongoClients.create(settings);
			MongoDatabase database = mongoClient.getDatabase("ProjetoES");
			MongoCollection<Document> coll = database.getCollection("Eventos_"+username);

			MongoCursor<Document> cursor = coll.find().iterator();

			try {
				while(cursor.hasNext()) {
					String event = cursor.next().toJson();
					JSONObject json = (JSONObject) parser.parse(event);
					calendar.add(json);

				}
			} finally {
				cursor.close();
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;

	}

	/**
	 * Method to completely clear the Project database.
	 */
	public void clearDB() {
		try {
			ConnectionString connectionString = new ConnectionString("mongodb+srv://projetoesgrupo08:ProjetoESGrupo08@cluster0.qgzbwsz.mongodb.net/?retryWrites=true&w=majority");
			MongoClientSettings settings = MongoClientSettings.builder()
					.applyConnectionString(connectionString)
					.build();
			MongoClient mongoClient = MongoClients.create(settings);
			MongoDatabase database = mongoClient.getDatabase("ProjetoES");
			database.drop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to ensure that there is no duplicate data in each collection of the project database.
	 * @param coll
	 */
	private void dropCollection(MongoCollection<Document> coll) {
		coll.drop();
	}

	/**
	 * Replace webcal protocol from the Fenix URL with https protocol.
	 * @param str
	 * @return
	 */
	private String replace(String str) {
		return str.replace("webcal", "https");
	}

	/**
	 * Get username directly from the Fenix URL.
	 * @param str
	 * @return
	 */
	private String getUsername(String str) {
		return StringUtils.substringBetween(str, "username=", "@");
	}

}
