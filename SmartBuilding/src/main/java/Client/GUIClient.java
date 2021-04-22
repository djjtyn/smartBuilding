package Client;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import grpc.occupantService.Empty;
import grpc.occupantService.GymTrainer;
import grpc.occupantService.occupantServiceGrpc;
//import OccupantService requirements
import grpc.occupantService.occupantServiceGrpc.occupantServiceBlockingStub;
import grpc.occupantService.occupantServiceGrpc.occupantServiceStub;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class GUIClient {

	//Create occupant service stubs
	private static occupantServiceBlockingStub occupantBlockingStub;
	private static occupantServiceStub occupantAsyncStub;
	
	private ServiceInfo serviceInfo;
	
	private JFrame frame;
	private JTextField textNumber1, textNumber2;
	private JTextArea textResponse ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIClient window = new GUIClient();
					window.frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Create the Applications using class constructor
	public GUIClient() {
		String occupantService = "_occupantService._tcp.local.";
		discoverService(occupantService);
		int port = 50053;
		String host = "localhost";
		
		//Build the channel
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();
		
		//stubs -- generate from proto
		occupantBlockingStub = occupantServiceGrpc.newBlockingStub(channel);
		occupantAsyncStub = occupantServiceGrpc.newStub(channel);	
		
		initialize();
	}
	
	//Method to discover the occupant Services
	//THIS ISN'T GETTING THE CORRECT PORT!!!
	private void discoverService(String service) {	
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service,  new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("This is never called");
					System.out.println("Service resolved: " + event.getInfo());

					serviceInfo = event.getInfo();

					int port = serviceInfo.getPort();
					
					System.out.println("resolving " + service + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + serviceInfo.getNiceTextString());
					System.out.println("\t host: " + serviceInfo.getHostAddresses()[0]);					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Occupant Service removed: " + event.getInfo());			
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Service name: " + event.getInfo().getServer());
					System.out.println("Occupant Service added: " + event.getInfo());					
				}
			});
			
			// Wait a bit
			Thread.sleep(2000);
			
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	//Method to generate the GUI frame
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		JPanel panel_service_1 = new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton gymTrainerBtn = new JButton("List Gym Trainers");
		gymTrainerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empty request = Empty.newBuilder().build();
				//Response
					Iterator<GymTrainer> responseIterator = occupantBlockingStub.viewGymTrainers(request);
					int iterateCount = 1;
					while(responseIterator.hasNext()) {
						GymTrainer response = responseIterator.next();
						textResponse.append("***Gym Trainer " + iterateCount + "***\n" + response.toString() + "\n");
						System.out.println(response);
						iterateCount++;
					}		
			}
		});
		panel_service_1.add(gymTrainerBtn);
		
		textResponse = new JTextArea(20, 20);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_service_1.add(scrollPane);

	}
}
