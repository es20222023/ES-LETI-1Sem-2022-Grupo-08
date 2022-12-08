package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import com.davidmoodie.SwingCalendar.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
	static int count = 0;

	//	static String[] username_list = new String[3];

	public static void main(String[] args) {

		String[] url_list = new String[3];
		String[] username_list = new String[3];
		//		ArrayList<CalendarEvent> events = new ArrayList<>();
		HashMap<String, ArrayList<CalendarEvent>> events = new HashMap<String, ArrayList<CalendarEvent>>();


		JFrame start_frame = new JFrame(); // creates the starting frame
		JFrame calendar_frame = new JFrame(); // creates the calendar frame
		JFrame calendar_frame1 = new JFrame(); // creates the calendar frame
		JFrame calendar_frame2 = new JFrame(); // creates the calendar frame
		JFrame calendar_frame3 = new JFrame(); // creates the calendar frame
		//JLabel calendar_names = new JLabel();

		ImageIcon icon = new ImageIcon("iscte_logo.png");
		JLabel logo = new JLabel(icon);
		System.out.println(logo);

		JLabel calendars_label = new JLabel("Calendars");
		JLabel uri_label = new JLabel("Lorem Ipsum");
		JLabel graphics_label = new JLabel("Graphics");
		final JTextField input_URI = new JTextField("Paste your URI here"); //input box for ICS
		final JTextField input_person1 = new JTextField("username"); //input box for ICS
		final JTextField input_person2 = new JTextField("username"); //input box for ICS
		final JTextField input_person3 = new JTextField("username"); //input box for ICS

		JButton calendar_button = new JButton("All Events");
		calendar_button.addActionListener(new ActionListener() { // Hides starting frame and shows calendar frame
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<CalendarEvent> all_events = new ArrayList<>();
				DB_Operations entry = new DB_Operations();
				EventsFromDBToCalendar ev = new EventsFromDBToCalendar();
				Color[] color_list = {Color.cyan, Color.lightGray, Color.pink};
				int i = 0;
				for(String user : username_list) {
					all_events.addAll(ev.JSonObjectToCalendarEvent(entry.readFromDB(user), color_list[i++]));
					System.out.println(username_list[0]);
				}
				WeekCalendar cal = new WeekCalendar(all_events);
				cal.addCalendarEventClickListener(f -> System.out.println(f.getCalendarEvent()));
				cal.addCalendarEmptyClickListener(f -> {
					System.out.println(f.getDateTime());
					System.out.println(Calendar.roundTime(f.getDateTime().toLocalTime(), 30));
				});

				JButton goToTodayBtn = new JButton("Today");
				goToTodayBtn.addActionListener(f -> cal.goToToday());

				JButton nextWeekBtn = new JButton(">");
				nextWeekBtn.addActionListener(f -> cal.nextWeek());

				JButton prevWeekBtn = new JButton("<");
				prevWeekBtn.addActionListener(f -> cal.prevWeek());

				JButton nextMonthBtn = new JButton(">>");
				nextMonthBtn.addActionListener(f -> cal.nextMonth());

				JButton prevMonthBtn = new JButton("<<");
				prevMonthBtn.addActionListener(f -> cal.prevMonth());

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
				start_frame.setVisible(false);
				calendar_frame.setVisible(true);
			}
		});

		JButton uri_button = new JButton("Add URI");		
		uri_button.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				if(count < 3) {
					DB_Operations entry = new DB_Operations();
					url_list[count] = input_URI.getText();			
					entry.icsToDB(url_list[count]);
					count = count + 1;
				}
			}
		});

		JButton person1_cal = new JButton("Person1 Events");
		person1_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				DB_Operations entry = new DB_Operations();
				EventsFromDBToCalendar ev = new EventsFromDBToCalendar();
				events.put(username_list[0], ev.JSonObjectToCalendarEvent(entry.readFromDB(username_list[0]), Color.cyan));
				System.out.println(username_list[0]);
				WeekCalendar cal1 = new WeekCalendar(events.get(username_list[0]));

				cal1.addCalendarEventClickListener(f -> System.out.println(f.getCalendarEvent()));
				cal1.addCalendarEmptyClickListener(f -> {
					System.out.println(f.getDateTime());
					System.out.println(Calendar.roundTime(f.getDateTime().toLocalTime(), 30));
				});

				JButton goToTodayBtn = new JButton("Today");
				goToTodayBtn.addActionListener(f -> cal1.goToToday());

				JButton nextWeekBtn = new JButton(">");
				nextWeekBtn.addActionListener(f -> cal1.nextWeek());

				JButton prevWeekBtn = new JButton("<");
				prevWeekBtn.addActionListener(f -> cal1.prevWeek());

				JButton nextMonthBtn = new JButton(">>");
				nextMonthBtn.addActionListener(f -> cal1.nextMonth());

				JButton prevMonthBtn = new JButton("<<");
				prevMonthBtn.addActionListener(f -> cal1.prevMonth());

				JButton back_button = new JButton("Back");

				JPanel weekControls = new JPanel();
				weekControls.add(back_button);
				weekControls.add(prevMonthBtn);
				weekControls.add(prevWeekBtn);
				weekControls.add(goToTodayBtn);
				weekControls.add(nextWeekBtn);
				weekControls.add(nextMonthBtn);

				calendar_frame1.add(weekControls, BorderLayout.NORTH);

				calendar_frame1.add(cal1, BorderLayout.CENTER);
				calendar_frame1.setSize(1000, 900);

				back_button.setBounds(50, 2, 50, 35);

				back_button.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
					@Override
					public void actionPerformed(ActionEvent e) {
						start_frame.setVisible(true);
						calendar_frame1.dispose();
					}
				});
				// calendar_frame.setVisible(true);
				calendar_frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				start_frame.setVisible(false);
				calendar_frame1.setVisible(true);
			}
		});

		JButton person2_cal = new JButton("Person2 Events");
		person2_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				DB_Operations entry = new DB_Operations();
				EventsFromDBToCalendar ev = new EventsFromDBToCalendar();
				events.put(username_list[1], ev.JSonObjectToCalendarEvent(entry.readFromDB(username_list[1]), Color.lightGray));
				System.out.println(username_list[1]);
				WeekCalendar cal2 = new WeekCalendar(events.get(username_list[1]));

				cal2.addCalendarEventClickListener(f -> System.out.println(f.getCalendarEvent()));
				cal2.addCalendarEmptyClickListener(f -> {
					System.out.println(f.getDateTime());
					System.out.println(Calendar.roundTime(f.getDateTime().toLocalTime(), 30));
				});

				JButton goToTodayBtn = new JButton("Today");
				goToTodayBtn.addActionListener(f -> cal2.goToToday());

				JButton nextWeekBtn = new JButton(">");
				nextWeekBtn.addActionListener(f -> cal2.nextWeek());

				JButton prevWeekBtn = new JButton("<");
				prevWeekBtn.addActionListener(f -> cal2.prevWeek());

				JButton nextMonthBtn = new JButton(">>");
				nextMonthBtn.addActionListener(f -> cal2.nextMonth());

				JButton prevMonthBtn = new JButton("<<");
				prevMonthBtn.addActionListener(f -> cal2.prevMonth());

				JButton back_button = new JButton("Back");

				JPanel weekControls = new JPanel();
				weekControls.add(back_button);
				weekControls.add(prevMonthBtn);
				weekControls.add(prevWeekBtn);
				weekControls.add(goToTodayBtn);
				weekControls.add(nextWeekBtn);
				weekControls.add(nextMonthBtn);

				calendar_frame2.add(weekControls, BorderLayout.NORTH);

				calendar_frame2.add(cal2, BorderLayout.CENTER);
				calendar_frame2.setSize(1000, 900);

				back_button.setBounds(50, 2, 50, 35);

				back_button.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
					@Override
					public void actionPerformed(ActionEvent e) {
						start_frame.setVisible(true);
						calendar_frame2.dispose();
					}
				});
				// calendar_frame.setVisible(true);
				calendar_frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				start_frame.setVisible(false);
				calendar_frame2.setVisible(true);
			}
		});

		JButton person3_cal = new JButton("Person3 Events");
		person3_cal.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				DB_Operations entry = new DB_Operations();
				EventsFromDBToCalendar ev = new EventsFromDBToCalendar();
				events.put(username_list[2], ev.JSonObjectToCalendarEvent(entry.readFromDB(username_list[2]), Color.pink));
				System.out.println(username_list[2]);
				WeekCalendar cal3 = new WeekCalendar(events.get(username_list[2]));

				cal3.addCalendarEventClickListener(f -> System.out.println(f.getCalendarEvent()));
				cal3.addCalendarEmptyClickListener(f -> {
					System.out.println(f.getDateTime());
					System.out.println(Calendar.roundTime(f.getDateTime().toLocalTime(), 30));
				});

				JButton goToTodayBtn = new JButton("Today");
				goToTodayBtn.addActionListener(f -> cal3.goToToday());

				JButton nextWeekBtn = new JButton(">");
				nextWeekBtn.addActionListener(f -> cal3.nextWeek());

				JButton prevWeekBtn = new JButton("<");
				prevWeekBtn.addActionListener(f -> cal3.prevWeek());

				JButton nextMonthBtn = new JButton(">>");
				nextMonthBtn.addActionListener(f -> cal3.nextMonth());

				JButton prevMonthBtn = new JButton("<<");
				prevMonthBtn.addActionListener(f -> cal3.prevMonth());

				JButton back_button = new JButton("Back");

				JPanel weekControls = new JPanel();
				weekControls.add(back_button);
				weekControls.add(prevMonthBtn);
				weekControls.add(prevWeekBtn);
				weekControls.add(goToTodayBtn);
				weekControls.add(nextWeekBtn);
				weekControls.add(nextMonthBtn);

				calendar_frame3.add(weekControls, BorderLayout.NORTH);

				calendar_frame3.add(cal3, BorderLayout.CENTER);
				calendar_frame3.setSize(1000, 900);

				back_button.setBounds(50, 2, 50, 35);

				back_button.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
					@Override
					public void actionPerformed(ActionEvent e) {
						start_frame.setVisible(true);
						calendar_frame3.dispose();
					}
				});
				// calendar_frame.setVisible(true);
				calendar_frame3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				start_frame.setVisible(false);
				calendar_frame3.setVisible(true);
			}
		});

		JButton button_person1 = new JButton("Set");
		button_person1.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				username_list[0] = input_person1.getText();
				System.out.println(username_list[0]);
			}
		});

		JButton button_person2 = new JButton("Set");
		button_person2.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				username_list[1] = input_person2.getText();
				System.out.println(username_list[1]);
			}
		});

		JButton button_person3 = new JButton("Set");
		button_person3.addActionListener(new ActionListener() { // Action to be performed button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				username_list[2] = input_person3.getText();
				System.out.println(username_list[2]);
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
		input_person1.setBounds(600, 131, 100, 30);
		input_person2.setBounds(600, 162, 100, 30);
		input_person3.setBounds(600, 193, 100, 30);
		uri_button.setBounds(50, 150, 150, 30);		
		calendar_button.setBounds(440, 100, 150, 30);
		button_person1.setBounds(710, 131, 70, 30);
		button_person2.setBounds(710, 162, 70, 30);
		button_person3.setBounds(710, 193, 70, 30);
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
		start_frame.add(input_person1);
		start_frame.add(input_person2);
		start_frame.add(input_person3);
		start_frame.add(button_person1);
		start_frame.add(button_person2);
		start_frame.add(button_person3);







		//		events.add(new CalendarEvent(LocalDate.of(2022, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20),
		//				"Test 11/11 14:00-14:20"));
		//		WeekCalendar cal = new WeekCalendar(events);
		//
		//		cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
		//		cal.addCalendarEmptyClickListener(e -> {
		//			System.out.println(e.getDateTime());
		//			System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
		//		});
		//
		//		JButton goToTodayBtn = new JButton("Today");
		//		goToTodayBtn.addActionListener(e -> cal.goToToday());
		//
		//		JButton nextWeekBtn = new JButton(">");
		//		nextWeekBtn.addActionListener(e -> cal.nextWeek());
		//
		//		JButton prevWeekBtn = new JButton("<");
		//		prevWeekBtn.addActionListener(e -> cal.prevWeek());
		//
		//		JButton nextMonthBtn = new JButton(">>");
		//		nextMonthBtn.addActionListener(e -> cal.nextMonth());
		//
		//		JButton prevMonthBtn = new JButton("<<");
		//		prevMonthBtn.addActionListener(e -> cal.prevMonth());
		//
		//		JButton back_button = new JButton("Back");
		//		
		//		JPanel weekControls = new JPanel();
		//		weekControls.add(back_button);
		//		weekControls.add(prevMonthBtn);
		//		weekControls.add(prevWeekBtn);
		//		weekControls.add(goToTodayBtn);
		//		weekControls.add(nextWeekBtn);
		//		weekControls.add(nextMonthBtn);
		//
		//		calendar_frame.add(weekControls, BorderLayout.NORTH);
		//
		//		calendar_frame.add(cal, BorderLayout.CENTER);
		//		calendar_frame.setSize(1000, 900);
		//		
		//		back_button.setBounds(50, 2, 50, 35);
		//		
		//		back_button.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				start_frame.setVisible(true);
		//				calendar_frame.dispose();
		//			}
		//		});
		//		// calendar_frame.setVisible(true);
		//		calendar_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
