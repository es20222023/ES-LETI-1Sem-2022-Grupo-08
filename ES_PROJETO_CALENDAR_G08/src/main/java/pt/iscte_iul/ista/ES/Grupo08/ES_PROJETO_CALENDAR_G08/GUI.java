package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class GUI {

	static String names = "";
	private static JLabel calendar_names = new JLabel();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		initial_frame();
	}
	private static void initial_frame() throws Exception {
	JFrame initial_frame = new JFrame("First Frame");
	JButton b=new JButton("Add Calendar"); 
	//private JLabel calendar_names = new JLabel();
	calendar_names.setText("test");
	calendar_names.setBounds(500, 50, 100, 45);
    b.setBounds(50, 100, 150, 30); 
    final JTextField input_name = new JTextField("username");
    final JTextField input_URI = new JTextField("Paste your URI here");
	input_name.setBounds(50, 50, 100, 45);
	input_URI.setBounds(151, 50, 200, 45);
    initial_frame.setSize(800, 800);  
    initial_frame.setLayout(null);  
    initial_frame.setVisible(true);   
    initial_frame.add(b);
    initial_frame.add(input_name);
    initial_frame.add(input_URI);
    initial_frame.add(calendar_names);
    b.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
        	names = names + (input_name.getText()+"</br>");
            calendar_names.setText(names);
            
            
            

        }
    });
    
    }
	
	
	
}
