package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import com.davidmoodie.SwingCalendar.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		
		JFrame start_frame = new JFrame(); //creates the starting frame
		JLabel calendar_names = new JLabel();
		JButton calendar_button = new JButton("Add Calendar");
		JButton uri_button = new JButton("Add URI");
		final JTextField input_URI = new JTextField("Paste your URI here");
		input_URI.setBounds(50, 50, 200, 45);
		start_frame.setSize(800, 800);  
	    start_frame.setLayout(null);  
	    start_frame.setVisible(true);   
	    start_frame.add(calendar_button);
	    start_frame.add(input_URI);
	    start_frame.add(calendar_names);
	    calendar_button.setBounds(50, 100, 150, 30); 
	    calendar_button.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        }
	    });
		
		
		
		
		
		
		
		
		
		JFrame calendar_frame = new JFrame(); //creates the calendar frame
		
		ArrayList<CalendarEvent> events = new ArrayList<>();
		events.add(new CalendarEvent(LocalDate.of(2022, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));		
		WeekCalendar cal = new WeekCalendar(events);
		
		cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
        cal.addCalendarEmptyClickListener(e -> {
            System.out.println(e.getDateTime());
            System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
        });

        JButton goToTodayBtn = new JButton("Today");
        goToTodayBtn.addActionListener(e -> cal.goToToday());

        JButton nextWeekBtn = new JButton(">");
        nextWeekBtn.addActionListener(e -> cal.nextWeek());

        JButton prevWeekBtn = new JButton("<");
        prevWeekBtn.addActionListener(e -> cal.prevWeek());

        JButton nextMonthBtn = new JButton(">>");
        nextMonthBtn.addActionListener(e -> cal.nextMonth());

        JButton prevMonthBtn = new JButton("<<");
        prevMonthBtn.addActionListener(e -> cal.prevMonth());

        JPanel weekControls = new JPanel();
        weekControls.add(prevMonthBtn);
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(nextMonthBtn);

        calendar_frame.add(weekControls, BorderLayout.NORTH);

        calendar_frame.add(cal, BorderLayout.CENTER);
        calendar_frame.setSize(1000, 900);
        //calendar_frame.setVisible(true);
        calendar_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
