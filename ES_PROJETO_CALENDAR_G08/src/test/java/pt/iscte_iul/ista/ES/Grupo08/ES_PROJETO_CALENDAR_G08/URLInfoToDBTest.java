package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class URLInfoToDBTest {

	@Before
	public void setup() {
		URLInfoToDB.icsToDB();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		JSONArray events = URLInfoToDB.readFromDB();
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

}
