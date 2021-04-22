package Client;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import grpc.occupantService.Empty;
import grpc.occupantService.GymTrainer;
import grpc.occupantService.occupantServiceGrpc;
//import OccupantService requirements
import grpc.occupantService.occupantServiceGrpc.occupantServiceBlockingStub;
import grpc.occupantService.occupantServiceGrpc.occupantServiceStub;
import grpc.lightingService.LightingResponse;
import grpc.lightingService.Room;
import grpc.lightingService.RoomDb;
import grpc.lightingService.lightingGrpc;
//import LightingService requirements
import grpc.lightingService.lightingGrpc.lightingBlockingStub;
import grpc.lightingService.lightingGrpc.lightingStub;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
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
	// This arraylist will contain the database for the rooms
	ArrayList<RoomDb> rooms = new ArrayList<RoomDb>();

	// Create occupant service stubs
	private static occupantServiceBlockingStub occupantBlockingStub;
	private static occupantServiceStub occupantAsyncStub;
	// Create lighting service stubs
	private static lightingBlockingStub lightingBlockingStub;
	private static lightingStub lightingAsyncStub;
	private ServiceInfo serviceInfo;

	private JFrame frame;
	private JTextField textInput;
	private JTextArea textResponse;
	private JTextArea singleLightResponse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIClient window = new GUIClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the Applications using class constructor
	public GUIClient() {

		// Create some room records
		for (int i = 0; i < 30; i++) {
			rooms.add(i, new RoomDb((i + 1), "Room " + (1 + i), 0, 0, 0.0));
		}

		// Build the channels
		// (1)Occupant Service Channel
		String occupantService = "_occupantService._tcp.local.";
		discoverService(occupantService);
		int occupantPort = 50053;
		String host = "localhost";
		ManagedChannel occupantChannel = ManagedChannelBuilder.forAddress(host, occupantPort).usePlaintext().build();
		occupantBlockingStub = occupantServiceGrpc.newBlockingStub(occupantChannel);
		occupantAsyncStub = occupantServiceGrpc.newStub(occupantChannel);

		// (2)Lighting Service Channel
		String lightingService = "_lightingService._tcp.local.";
		discoverService(lightingService);
		int lightingPort = 50052;
		ManagedChannel lightingChannel = ManagedChannelBuilder.forAddress(host, lightingPort).usePlaintext().build();
		lightingBlockingStub = lightingGrpc.newBlockingStub(lightingChannel);
		lightingAsyncStub = lightingGrpc.newStub(lightingChannel);


		initialize();
	}

	// Method to discover the occupant Services
	// THIS ISN'T GETTING THE CORRECT PORT!!!
	private void discoverService(String service) {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("This is never called");
					System.out.println("Service resolved: " + event.getInfo());

					serviceInfo = event.getInfo();

					int port = serviceInfo.getPort();

					System.out.println("resolving " + service + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
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

	// Method to generate the GUI frame
	private <E> void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);

		frame.getContentPane().setLayout(bl);

		// View Gym Trainers
		JPanel panel_service_1 = new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton gymTrainerBtn = new JButton("List Gym Trainers");
		panel_service_1.add(gymTrainerBtn);
		gymTrainerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empty request = Empty.newBuilder().build();
				// Response
				Iterator<GymTrainer> responseIterator = occupantBlockingStub.viewGymTrainers(request);
				int iterateCount = 1;
				while (responseIterator.hasNext()) {
					GymTrainer response = responseIterator.next();
					textResponse.append("***Gym Trainer " + iterateCount + "***\n" + response.toString() + "\n");
					iterateCount++;
				}
			}
		});
		textResponse = new JTextArea(20, 20);
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textResponse);
		panel_service_1.add(scrollPane);

		//Control Lighting
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		panel_service_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JComboBox roomList = new JComboBox();
		for(RoomDb room: rooms) {
			roomList.addItem(room.getRoomName());
		}
		panel_service_2.add(roomList);
		JButton btnCalculate = new JButton("Adjust Lighting to ");
		frame.getContentPane().add(panel_service_2);
		JLabel lighting = new JLabel("Adjust lighting to: ");
		panel_service_2.add(lighting); 
		textInput = new JTextField();
		panel_service_2.add(textInput);
		textInput.setColumns(3);
		JLabel lightingAdjust = new JLabel("Percent");
		panel_service_2.add(lightingAdjust);
		JButton singleLightAdjust = new JButton("Adjust Lighting");
		panel_service_2.add(singleLightAdjust);
		singleLightAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Request
				int roomIndex = roomList.getSelectedIndex();
				int lightAdjustment = Integer.parseInt(textInput.getText());
				Room lightRequest = Room.newBuilder().setBrightness(rooms.get(roomIndex).getBrightness()).setRoomName(rooms.get(roomIndex).getRoomName()).setIntAdjust(lightAdjustment)
								.build();
				//Response
				LightingResponse lightResponse = lightingBlockingStub.adjustLighting(lightRequest);
				System.out.println(lightResponse.getLightingMessage());
				singleLightResponse.append(lightResponse.getLightingMessage());
				//Set the rooms instance brightness value after the change is made
				rooms.get(roomIndex).setBrightness(lightResponse.getBrightnessValue());
			}
		});
		singleLightResponse = new JTextArea(5, 100);
		singleLightResponse.setLineWrap(true);
		singleLightResponse.setWrapStyleWord(true);
		JScrollPane scrollPane2 = new JScrollPane(singleLightResponse);
		panel_service_2.add(scrollPane2);

	}

	// A search method to make get the index numbers of the data input
	public static int binarySearch(ArrayList<RoomDb> arr, int start, int end, int searchKey) {
		// if the end index of the array list is equal to or higher than the starting
		// index
		int middle = 0;
		if (end >= start) {
			// get the middle index of the array
			middle = start + (end - start) / 2;
			// if the middle index value is the search key(Occupant Id) return that index
			if (arr.get(middle).getId() == searchKey) {
				return middle;
				// if the middle index is greater than the search key recursively call the
				// method
			} else if (arr.get(middle).getId() > searchKey) {
				return binarySearch(arr, start, middle - 1, searchKey);
				// if the middle index is less than the search key recursively call the method
			} else if (arr.get(middle).getId() < searchKey) {
				return binarySearch(arr, middle + 1, end, searchKey);
			}
		}
		return middle;
	}
}
