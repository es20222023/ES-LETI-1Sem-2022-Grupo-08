package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.davidmoodie.SwingCalendar.CalendarEvent;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class TestEventsFromDBToCalendar {

	EventsFromDBToCalendar ev;
	JSONArray events;
	DB_Operations entry;
	
	MongoDatabase database;
	

	@Before 
	public void setup() {
		ev = new EventsFromDBToCalendar();
		ConnectionString connectionString = new ConnectionString("mongodb+srv://projetoesgrupo08:ProjetoESGrupo08@cluster0.qgzbwsz.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		entry = new DB_Operations();
		MongoDatabase database = mongoClient.getDatabase("ProjetoES");
		if(!database.listCollectionNames().into(new ArrayList<Object>()).contains("Eventos_pmaal1"))
			entry.icsToDB("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");
		events = entry.readFromDB("pmaal1");
	}
	
	@Test
	public void testJSonObjectToCalendarEvent() {
		ArrayList<CalendarEvent> list = ev.JSonObjectToCalendarEvent(events, Color.blue);
		
		CalendarEvent event = list.get(0);
		assertTrue(event.getDate().getClass() == LocalDate.class);
		assertFalse(!(event.getDate().getClass() == LocalDate.class));
		
		
	}
	

}
