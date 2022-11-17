package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import java.util.ArrayList;
import java.time.*;
import com.davidmoodie.SwingCalendar.*;

public class App {

	public static void main(String[] args) {
		
		ArrayList<CalendarEvent> events = new ArrayList<>();
		events.add(new CalendarEvent(LocalDate.of(2022, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));
		
		Calendar cal = new WeekCalendar(events);

	}

}
