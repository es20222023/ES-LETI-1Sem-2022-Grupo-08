package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;

import org.json.simple.*;
import org.apache.commons.lang3.StringUtils;
/**
 * Hello world!
 *
 */
public class TestWebCalendarToJSON 
{
	public static void main( String[] args ) throws Exception {

		icsToJson();

	}


	@SuppressWarnings("unchecked")
	private static void icsToJson() {
		try {
			URL url = new URL("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");

			Scanner s = new Scanner(url.openStream());
			JSONArray calendar = new JSONArray();

			FileWriter file = new FileWriter("output.json");
			ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);



			String line = s.nextLine();
			while (s.hasNext()) {
				if(line.contains("DTSTART")) {

					JSONObject obj = new JSONObject();
					obj.put("START", StringUtils.substringAfter(line, ":"));
					line = s.nextLine();
					obj.put("END", StringUtils.substringAfter(line, ":"));
					line = s.nextLine();
					obj.put("EVENT", StringUtils.substringAfter(line, ":"));

					//Creates an event object that contains de json object that contains the details

					JSONObject event = new JSONObject();
					event.put("EVENT", obj);

					calendar.add(event);


					line = s.nextLine();  

				} else line = s.nextLine();

			}
			mapper.writeValue(file, calendar);
			
			file.close();
			System.out.println(calendar.toJSONString());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	} 





}
