package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import java.util.regex.Pattern;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;


/**
 * Hello world!
 *
 */
public class TestWebCalendarToJSON 
{
    public static void main( String[] args ) throws Exception {
    	
    	String URL = readUrl("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=pmaal1@iscte.pt&password=4nW90X1wHzGP2YQc5ardt24MEz9hEACP0uss6KwnUXgO76bZcF2NLXjzdmqaF738FVbA9Uhu3ADP5pAMVBkftzHfDvzoMBMe5jPdWVRboFdCpfW02WbnAnSN6eWkeGd7");
    	//System.out.println(URL);
    	
    	//System.out.println(URL.toString());
    	String[] strArray = StrtoStrArray(URL);
    	/*for (int i = 0; i< strArray.length; i++){  
    		System.out.println(strArray[i]); 
    	}*/
    	String jsonArray = jSonArray(strArray);
    	Component cal = getCalendarEvent(URL); 
    	System.out.println(cal.toString());
    	
    	
    	
    	
    	
   
}
    public static Component getCalendarEvent(String myCalendarString) 
    {
        try {
            StringReader sin = new StringReader(myCalendarString);
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(sin);

            return (Component)calendar.getComponents("VEVENT");

        } catch (Exception e) {e.printStackTrace();}

        return null;
    }
    
    
    
    private static String jSonArray(String[] strArray) {
    	Gson gson=new GsonBuilder().create();
        String jsonArray=gson.toJson(strArray);
        return jsonArray;
    	
    }
    
    private static String[] StrtoStrArray(String calString) {
    	//declaring an empty string array  
    	String[] strArray = null;  
    	//splitting the string with delimiter as BEGIN  
    	
    	String patternStr = "BEGIN";  
    	Pattern ptr = Pattern.compile(patternStr);  
    	
    	//storing the string elements in array after splitting  
    	strArray = ptr.split(calString);
    	return strArray;
    }
    
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
