package pt.iscte_iul.ista.ES.Grupo08.ES_PROJETO_CALENDAR_G08;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class GUI {

	static String var;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initial_frame();
	}
	
	private static void initial_frame() {
	JFrame initial_frame = new JFrame("First Frame");
	JButton b=new JButton("Click Here");  
    b.setBounds(50,100,95,30); 
    final JTextField input1 = new JTextField("Paste your URI here");
	input1.setBounds(50,50, 200, 45);
    initial_frame.setSize(400,400);  
    initial_frame.setLayout(null);  
    initial_frame.setVisible(true);   
    initial_frame.add(b);
    initial_frame.add(input1);
    input1.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            var = input1.getText(); // Getting text input from JTextField(tf)
            System.out.println(var);
        }
    });
    
	}
	
	
//	private static Component uri_input() {
//		
//		JTextField input1 = new JTextField("Paste your URI here");
//		input1.setBounds(50,50, 200, 45);
//		return input1;		
//		
//		input1.addActionListener(new ActionListener() { // Action to be performed when "Enter" key is pressed
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                var = input1.getText(); 
//            }
//	}
}
