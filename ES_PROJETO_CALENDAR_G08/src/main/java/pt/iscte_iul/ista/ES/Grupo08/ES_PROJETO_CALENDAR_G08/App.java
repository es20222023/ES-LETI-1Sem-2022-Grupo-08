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

		JFrame start_frame = new JFrame(); // creates the starting frame
		JFrame calendar_frame = new JFrame(); // creates the calendar frame
		//JLabel calendar_names = new JLabel();
		
		ImageIcon icon = new ImageIcon("iscte_logo.png");
		JLabel logo = new JLabel(icon);
		System.out.println(logo);
		
		JLabel calendars_label = new JLabel("Calendars");
		JLabel uri_label = new JLabel("Lorem Ipsum");
		JLabel graphics_label = new JLabel("Graphics");
		final JTextField input_URI = new JTextField("Paste your URI here"); //input box for ICS
		
		JButton calendar_button = new JButton("All Events");
		calendar_button.addActionListener(new ActionListener() { // Hides starting frame and shows calendar frame
			@Override
			public void actionPerformed(ActionEvent e) {
				start_frame.setVisible(false);
				calendar_frame.setVisible(true);
			}
		});
		
		JButton uri_button = new JButton("Add URI");		
		uri_button.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to save URIs
			}
		});
		
		JButton person1_cal = new JButton("Person1 Events");
		person1_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to show person1 calendar
			}
		});
		
		JButton person2_cal = new JButton("Person2 Events");
		person2_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to show person2 calendar
			}
		});
		
		JButton person3_cal = new JButton("Person3 Events");
		person3_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to show person3 calendar
			}
		});
		
		JButton graphic1 = new JButton("graphic 1");
		person3_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to show graphics
			}
		});

		JButton graphic2 = new JButton("graphic 2");
		person3_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to show graphics
			}
		});
		
		JButton graphic3 = new JButton("graphic 2");
		person3_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				// add methods to show graphics
			}
		});
		uri_label.setBounds(50, 70, 150, 30);
		calendars_label.setBounds(440, 70, 150, 30);
		graphics_label.setBounds(440, 250, 150, 30);
		logo.setBounds(650, 50, 200, 200);
		input_URI.setBounds(50, 100, 200, 45);
		uri_button.setBounds(50, 150, 150, 30);		
		calendar_button.setBounds(440, 100, 150, 30);
		person1_cal.setBounds(440, 131, 150, 30);
		person2_cal.setBounds(440, 162, 150, 30);
		person3_cal.setBounds(440, 193, 150, 30);
		graphic1.setBounds(440, 280, 150, 30);
		graphic2.setBounds(440, 311, 150, 30);
		graphic3.setBounds(440, 342, 150, 30);
		start_frame.setSize(800, 800);
		start_frame.setLayout(null);
		start_frame.setVisible(true);
		start_frame.add(input_URI);
		//start_frame.add(calendar_names);
		start_frame.add(logo);
		start_frame.add(calendars_label);
		start_frame.add(uri_label);
		start_frame.add(graphics_label);
		start_frame.add(calendar_button);
		start_frame.add(uri_button);
		start_frame.add(person1_cal);
		start_frame.add(person2_cal);
		start_frame.add(person3_cal);
		start_frame.add(graphic1);
		start_frame.add(graphic2);
		start_frame.add(graphic3);
		
		
		
		

		ArrayList<CalendarEvent> events = new ArrayList<>();
		events.add(new CalendarEvent(LocalDate.of(2022, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20),
				"Test 11/11 14:00-14:20"));
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

		JButton back_button = new JButton("Back");
		
		JPanel weekControls = new JPanel();
		weekControls.add(back_button);
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);

		calendar_frame.add(weekControls, BorderLayout.NORTH);

		calendar_frame.add(cal, BorderLayout.CENTER);
		calendar_frame.setSize(1000, 900);
		
		back_button.setBounds(50, 2, 50, 35);
		
		back_button.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				start_frame.setVisible(true);
				calendar_frame.dispose();
			}
		});
		// calendar_frame.setVisible(true);
		calendar_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
