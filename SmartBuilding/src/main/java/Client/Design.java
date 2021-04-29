package Client;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import grpc.elevatorService.OccupantDb;

public class Design {
	
	public static void main(String[] args) {
		initialiseStartPanel();
		
	}
	static <E> JPanel initialiseStartPanel() {
	JFrame frame = new JFrame();
	frame.setTitle("Client - Service Controller");
	frame.setBounds(140, 140, 540, 340);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);

	frame.getContentPane().setLayout(bl);
	JPanel panel = new JPanel();
	panel.setLayout(null);
	JComboBox<Integer> occupantList = new JComboBox<Integer>();
	occupantList.setBounds(180, 11, 57, 22);
	panel.add(occupantList);
	//Label for the timer
	JLabel timerValue = new JLabel("");
	panel.add(timerValue);
	// Elevators 1-3
	JButton elevatorRequest1 = new JButton("Request elevator 1");
	elevatorRequest1.setBounds(5, 219, 150, 23);
	panel.add(elevatorRequest1);
	JButton elevatorRequest2 = new JButton("Request elevator 2");
	elevatorRequest2.setBounds(5, 266, 150, 23);
	panel.add(elevatorRequest2);
	JButton elevatorRequest3 = new JButton("Request elevator 3");
	elevatorRequest3.setBounds(281, 219, 150, 23);
	panel.add(elevatorRequest3);
	JTextField textInput;
	textInput = new JTextField("I am testing");
	textInput.setBounds(281, 219, 150, 23);
	panel.add(textInput);
	frame.getContentPane().add(panel);
	
	JLabel lblNewLabel = new JLabel("Testing");
	lblNewLabel.setBounds(207, 223, 46, 14);
	panel.add(lblNewLabel);
	frame.setVisible(true);
	return panel;
	}
}
