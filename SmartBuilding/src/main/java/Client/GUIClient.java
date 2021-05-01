package Client;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//import OccupantService requirements
import grpc.occupantService.Empty;
import grpc.occupantService.GymTrainer;
import grpc.occupantService.occupantServiceGrpc;
import grpc.occupantService.occupantServiceGrpc.occupantServiceBlockingStub;
//import LightingService requirements
import grpc.lightingService.LightingResponse;
import grpc.lightingService.Room;
import grpc.lightingService.RoomDb;
import grpc.lightingService.lightingGrpc;
import grpc.lightingService.lightingGrpc.lightingBlockingStub;
import grpc.lightingService.lightingGrpc.lightingStub;
//import ElevatorService requirements
import grpc.elevatorService.Elevator;
import grpc.elevatorService.ElevatorDb;
import grpc.elevatorService.ElevatorRequest;
import grpc.elevatorService.ElevatorResponse;
import grpc.elevatorService.Occupant;
import grpc.elevatorService.OccupantDb;
import grpc.elevatorService.elevatorGrpc;
import grpc.elevatorService.elevatorGrpc.elevatorStub;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class GUIClient {
	// This array list will contain the database for the rooms
	ArrayList<RoomDb> rooms = new ArrayList<RoomDb>();

	// This array list will contain the database for the occupants
	static ArrayList<OccupantDb> occupants = new ArrayList<>();

	// This array list will contain the elevator details
	static ArrayList<ElevatorDb> elevators = new ArrayList<>();
	
	//This array list will ensure the people counter doesn't increase with a duplicate request
	ArrayList<Integer> peopleInElevator = new ArrayList<>();
	
	//This array list will ensure the room counter doesn't increase with a duplicate request
	ArrayList<Integer> roomCount = new ArrayList<>();

	// Create occupant stub(Only Blocking Stub needed)
	private static occupantServiceBlockingStub occupantBlockingStub;
	// Create lighting stubs(Blocking Stub and Async Stubs needed)
	private static lightingBlockingStub lightingBlockingStub;
	private static lightingStub lightingAsyncStub;
	// Create elevator stubs(Only Async Stub needed)
	private static elevatorStub elevatorAsyncStub;

	// Elements needed for the JFrame GUI
	private JFrame frame = new JFrame();
	private JTextField textInput;
	private JTextArea serverResponse;

	ServiceInfo serviceInfo;

	// This will count the amount of streams passed in streaming methods
	int streamCounter = 0;

	// This will be used to create a timer and keep track of its value
	Timer swingTimer;
	int timerCount = 0;

	public static void main(String[] args) throws FileNotFoundException {
		String dir = System.getProperty("user.dir"); // Get the users current directory to be used with file location
														// below
		Scanner sc = new Scanner(new File(dir + "/src/main/resources/elevatorService/occupantData.csv"));
		// Initialise the array list that will be used for storing the database info
		String dbHeadings = sc.nextLine(); // this is just so the data headings aren't read
		try {
			int i = 0;
			String st = "";
			while (sc.hasNextLine()) { // traverse the entire database and create OccupantDb records from it
				st = sc.nextLine();
				st = st.replace("\"", "");
				String[] data = st.split(",");
				occupants.add(i, new OccupantDb(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
						Integer.parseInt(data[3]), Integer.parseInt(data[4])));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create an elevator instance
		elevators.add(0, new ElevatorDb(1, 0, 0, 0, false));
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

		/* Build the channels(My local machine is having issues retrieving the service info from the discoverServices method so 
		** I have made use of try/catch blocks to hard code the host and ports with the appropriate details if needed)*/
		// (1)Occupant Service Channel
		String occupantService = "_http._tcp.local_occupantService.";
		String occupantHost;
		int occupantPort;
		try {
			discoverService(occupantService);
			occupantHost = serviceInfo.getHostAddresses()[0];
			occupantPort = serviceInfo.getPort();
		}catch(RuntimeException e) {
			occupantHost = "localhost";
			occupantPort = 50053;
		}
		ManagedChannel occupantChannel = ManagedChannelBuilder.forAddress(occupantHost,occupantPort).usePlaintext().build();
		occupantBlockingStub = occupantServiceGrpc.newBlockingStub(occupantChannel);
		System.out.println("Occupant service Added on port " + occupantPort + ". Host: " + occupantHost);

		// (2)Lighting Service Channel
		String lightingService = "_http._tcp.local_lightingService.";
		String lightingHost;
		int lightingPort;
		try {
			discoverService(lightingService);
			lightingHost = serviceInfo.getHostAddresses()[0];
			lightingPort = serviceInfo.getPort();
		}catch(RuntimeException e) {
			lightingHost = "localhost";
			lightingPort = 50052;
		}
		ManagedChannel lightingChannel = ManagedChannelBuilder.forAddress(lightingHost,lightingPort).usePlaintext().build();
		lightingBlockingStub = lightingGrpc.newBlockingStub(lightingChannel);
		lightingAsyncStub = lightingGrpc.newStub(lightingChannel);
		System.out.println("Lighting service Added on port " + lightingPort + ". Host: " + lightingHost);

		// (3)Elevator Service Channel
		String elevatorService = "_http._tcp.local_elevatorService.";
		String elevatorHost;
		int elevatorPort;
		try {
			discoverService(elevatorService);
			elevatorHost = serviceInfo.getHostAddresses()[0];
			elevatorPort = serviceInfo.getPort();
		}catch(RuntimeException e) {
			elevatorHost = "localhost";
			elevatorPort = 50051;
		}
		ManagedChannel elevatorChannel = ManagedChannelBuilder.forAddress(elevatorHost, elevatorPort).usePlaintext().build();
		elevatorAsyncStub = elevatorGrpc.newStub(elevatorChannel);
		System.out.println("Elevator service Added on port " + elevatorPort + ". Host: " + elevatorHost);

		initialiseStartPanel();

	}

	// Method to discover the services
	private void discoverService(String service) {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
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
					System.out.println("Service removed: " + event.getInfo());
				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Service added: " + event.getInfo());
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

	// Method to generate the GUI frame showing the different service types available
	private <E> JPanel initialiseStartPanel() {
		frame.setTitle("Client - Service Controller");
		frame.setBounds(140, 140, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.getContentPane().setLayout(bl);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		//Button for the Occupant(Gym) services
		JButton chooseOccupantService = new JButton("Choose occupant Service");
		chooseOccupantService.setBounds(27, 185, 402, 57);
		panel.add(chooseOccupantService);
		chooseOccupantService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(getOccupantServiceJPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		//Button for the lighting services
		JButton chooseLightControl = new JButton("Choose light control Service");
		chooseLightControl.setBounds(27, 117, 402, 57);
		panel.add(chooseLightControl);
		chooseLightControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(getLightServiceChoices());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		//Button for the Elevator Service
		JButton chooseElevator = new JButton("Choose Elevator Service");
		chooseElevator.setBounds(27, 49, 402, 57);
		panel.add(chooseElevator);
		chooseElevator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(getElevatorControl());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});	
		return panel;
	}
	
	
	// This is for the occupant service(SERVER STREAMING)
	private JPanel getOccupantServiceJPanel() {
		// View Gym Trainers
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton listTrainers = new JButton("List Gym Trainers");
		listTrainers.setBounds(109, 5, 196, 29);
		JButton serviceSelection = new JButton("Back to Service Selection");
		serviceSelection.setBounds(115, 5, 196, 29);
		panel.add(listTrainers);
		panel.add(serviceSelection);
		serverResponse = new JTextArea(20, 50);
		serverResponse.setLineWrap(true);
		serverResponse.setWrapStyleWord(true);
		serverResponse.setBounds(5, 20, 100, 30);
		JScrollPane scrollPane = new JScrollPane(serverResponse);
		panel.add(scrollPane);
		listTrainers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empty request = Empty.newBuilder().build();
				// Response
				try {
					Iterator<GymTrainer> responseIterator = occupantBlockingStub.viewGymTrainers(request);
					int iterateCount = 1;
					serverResponse.append("***SERVER STREAMING RESPONSE***\n");
					while (responseIterator.hasNext()) {
						GymTrainer response = responseIterator.next();
						serverResponse.append("\n");
						serverResponse.append("***ServerStream " + iterateCount + ": Gym Trainer " + iterateCount + "***\n" + response.toString() + "\n");
						iterateCount++;
					}
				} catch(StatusRuntimeException err) {
					err.printStackTrace();
				}
			}
		});
		//If this action is performed the user will be directed back to the starting GUI
		serviceSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(initialiseStartPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		return panel;
	}
	
	//This shows the options available for the lighting service(single or multiple room)
	private JPanel getLightServiceChoices() {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton singleRoom = new JButton("Adjust Lighting for ONE room");
		singleRoom.setBounds(41, 28, 359, 95);
		panel.add(singleRoom);
		singleRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(getSingleLightControl());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		JButton multiRoom = new JButton("Adjust Lighting for MULTIPLE rooms");
		multiRoom.setBounds(41, 142, 359, 95);
		panel.add(multiRoom);
		multiRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(selectRoomAmount());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		JButton serviceSelection = new JButton("Back to Service Selection");
		panel.add(serviceSelection);
		serviceSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(initialiseStartPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		return panel;
	}

	// This is for the lighting service for a single room
	private JPanel getSingleLightControl() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//roomList combo box will list all the available rooms
		JComboBox<String> roomList = new JComboBox<String>();
		for (RoomDb room : rooms) {
			roomList.addItem(room.getRoomName());
		}
		panel.add(roomList);
		JLabel serviceLabel = new JLabel("Adjust lighting to: ");
		panel.add(serviceLabel);
		//JComboBox for the adjustment levels(1-100)
		JComboBox<Integer> adjustmentLevel = new JComboBox<Integer>();
		for(int i = 0; i <=100;i++) {
			adjustmentLevel.addItem(i);
		}
		panel.add(adjustmentLevel);
		JLabel lightingAdjust = new JLabel("Percent");
		panel.add(lightingAdjust);
		JButton singleLightAdjust = new JButton("Adjust Lighting");
		panel.add(singleLightAdjust);
		JButton serviceSelection = new JButton("Back to Service Selection");
		panel.add(serviceSelection);
		JButton lightingSelection = new JButton("Back to Light adjustment options");
		panel.add(lightingSelection);
		serverResponse = new JTextArea(10, 60);
		serverResponse.setLineWrap(true);
		serverResponse.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(serverResponse);
		scrollPane.setBounds(5, 61, 700, 300);
		panel.add(scrollPane);
		singleLightAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Request
				int roomIndex = roomList.getSelectedIndex();
				int lightAdjustment = (adjustmentLevel.getSelectedIndex());
				if(lightAdjustment >=0 && lightAdjustment <= 100) {
					// Request
					Room lightRequest = Room.newBuilder().setBrightness(rooms.get(roomIndex).getBrightness())
						.setRoomName(rooms.get(roomIndex).getRoomName()).setIntAdjust(lightAdjustment).build();
					serverResponse.append("Client Request: Adjust lighting for " + rooms.get(roomIndex).getRoomName() + " from " + rooms.get(roomIndex).getBrightness() 
							+ "% to " + lightAdjustment + "%\n");
					// Response
					LightingResponse lightResponse = lightingBlockingStub.adjustLighting(lightRequest);
					serverResponse.append(lightResponse.getLightingMessage() + "\n");
					// Set the rooms instance brightness value after the change is made
					rooms.get(roomIndex).setBrightness(lightResponse.getBrightnessValue());
				}
			}
		});
		//If this action is performed the user will be redirected to the initial GUI screen
		serviceSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(initialiseStartPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		//If this action is performed the user will be redirected to the different lighting services available
		lightingSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(getLightServiceChoices());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		return panel;
	}

	// This is for selecting how many rooms the multi lighting will be used for
	private JPanel selectRoomAmount() {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel roomAmountSelection = new JLabel("How many rooms do you want to adjust the lighting for? ");
		panel.add(roomAmountSelection);
		// Create a JCOmbo box to diplay how many rooms there are using the room id's
		JComboBox<Integer> roomList = new JComboBox<Integer>();
		for (RoomDb room : rooms) {
			roomList.addItem(room.getId());
		}
		panel.add(roomList);
		JButton selectRoomAmount = new JButton("Select Amount");
		panel.add(selectRoomAmount);
		selectRoomAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int roomAmount = roomList.getSelectedIndex() + 1; // This will be used as the argument for the multi lighting method
				frame.setContentPane(getMultiLightControl(roomAmount));
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		JButton serviceSelection = new JButton("Back to Service Selection");
		panel.add(serviceSelection);
		serviceSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(initialiseStartPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		return panel;
	}

	// This is for setting the lighting service for a multiple rooms
	private JPanel getMultiLightControl(int roomAmount) {
		JPanel panel = new JPanel();
		//JComboBox for the room names
		JComboBox<String> roomList = new JComboBox<String>();
		for (RoomDb room : rooms) {
			roomList.addItem(room.getRoomName());
		}
		panel.add(roomList);
		JLabel multiLighting = new JLabel("Adjust lighting to: ");
		panel.add(multiLighting);
		//JComboBox for the adjustment levels(1-100)
		JComboBox<Integer> adjustmentLevel = new JComboBox<Integer>();
		for(int i = 0; i <=100;i++) {
			adjustmentLevel.addItem(i);
		}
		panel.add(adjustmentLevel);
		JLabel multiLightingAdjust = new JLabel("Percent");
		panel.add(multiLightingAdjust);
		JButton multiLightAdjust = new JButton("Adjust Lighting");
		panel.add(multiLightAdjust);
		JButton serviceSelection = new JButton("Back to Service Selection");
		serviceSelection.setBounds(21, 400, 200, 23);
		panel.add(serviceSelection);
		JButton lightingSelection = new JButton("Back to Light adjustment options");
		lightingSelection.setBounds(21, 900, 200, 23);

		panel.add(lightingSelection);
		serverResponse = new JTextArea(10, 60);
		serverResponse.setLineWrap(true);
		serverResponse.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(serverResponse);
		scrollPane.setBounds(5, 61, 500, 300);
		panel.add(scrollPane);
		StreamObserver<LightingResponse> responseObserver = new StreamObserver<LightingResponse>() {

			// This will return a single server response containing details of all the lighting adjustments made in the client stream
			@Override
			public void onNext(LightingResponse value) {
				serverResponse.append("***Server Response***\n");
				serverResponse.append(value.getLightingMessage() + "\n");
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				serverResponse.append("Light Control rpc is completed!! Amount of light adjustments made: " + streamCounter);
				// Reset the stream counter and clear the roomCOunt array list
				streamCounter = 0;
				roomCount.clear();
			}
		};
		// Request
		StreamObserver<Room> requestObserver = lightingAsyncStub.adjustLightingMultiRoom(responseObserver);
		multiLightAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Increase the count of streams
					if (streamCounter <= roomAmount) {
						int roomIndex = roomList.getSelectedIndex();
						int lightAdjustment = (adjustmentLevel.getSelectedIndex());
						if(lightAdjustment >=0 && lightAdjustment <= 100) {
							if(!roomCount.contains(roomIndex)) {
								roomCount.add(roomIndex);
								streamCounter++;
								requestObserver.onNext(Room.newBuilder().setBrightness(rooms.get(roomIndex).getBrightness())
									.setRoomName(rooms.get(roomIndex).getRoomName()).setIntAdjust(lightAdjustment).build());
								serverResponse.append("Client Request " + streamCounter + ": Adjust lighting for " + rooms.get(roomIndex).getRoomName() + " from " 
									+ rooms.get(roomIndex).getBrightness() + "% to " + lightAdjustment + "%\n");
								// set the instances value
								rooms.get(roomIndex).setBrightness(lightAdjustment);
								// If the stream amount gets to the roomAmount call oncompleted()
								if (streamCounter == roomAmount) {
									requestObserver.onCompleted();
								}
							}
						}
					}
				} catch(RuntimeException err) {
					err.printStackTrace();
				}
			}
		});
		serviceSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(initialiseStartPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		lightingSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(getLightServiceChoices());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		return panel;
	}
	

	// This is for the elevator service
	private JPanel getElevatorControl() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JComboBox<Integer> occupantList = new JComboBox<Integer>();
		panel.setLayout(null);
		for (OccupantDb occupant : occupants) {
			occupantList.addItem(occupant.getId());
		}
		occupantList.setBounds(180, 11, 57, 22);
		panel.add(occupantList);
		JButton elevatorRequest1 = new JButton("Request Elevator");
		elevatorRequest1.setBounds(280, 15, 150, 20);
		panel.add(elevatorRequest1);
		JButton serviceSelection = new JButton("Back to Service Selection");
		serviceSelection.setBounds(21, 400, 200, 23);
		panel.add(serviceSelection);
		serverResponse = new JTextArea(10, 100);
		serverResponse.setLineWrap(true);
		serverResponse.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(serverResponse);
		scrollPane.setBounds(5, 61, 700, 300);
		panel.add(scrollPane);
		JLabel serviceLabel = new JLabel("What is your occupant Id?");
		serviceLabel.setBounds(21, 15, 787, 14);
		panel.add(serviceLabel);
		
		StreamObserver<ElevatorResponse> responseObserver = new StreamObserver<ElevatorResponse>() {
			int currentFloor;

			@Override
			public void onNext(ElevatorResponse response) {
				serverResponse.append(response.getElevatorMessage() + "\n");
				currentFloor = response.getCurrentFloor();
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				//Set the elevator instances current floor
				elevators.get(0).setCurrentFloor(currentFloor);
				serverResponse.append("Elevator has finished its journey. Elevator currently on floor " + currentFloor);
				//Remove all people in the elevator from the array list containing their details
				//This array list will ensure the people counter doesn't increase when an occupant who has already requested the elevator requests it more than once 
				peopleInElevator.clear();
				swingTimer.restart();
				swingTimer.stop();
				}
		};
		// Request
		StreamObserver<ElevatorRequest> requestObserver = elevatorAsyncStub.moveElevator(responseObserver);
		//e1Timer is to create a incrementing timer every time the elevator 1 button is pressed
		ActionListener e1Timer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				timerCount++;
				//If the timer gets to 8 seconds call the onCOmpleted method
			  	if(timerCount >=8) {
			  		swingTimer.stop();
				    requestObserver.onCompleted();
				}
		    }
		}; 
		swingTimer = new Timer(1000, e1Timer);
		elevatorRequest1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Every time the button is pressed, reset the timerCount to 0 and start the timer again
					timerCount = 0;
					swingTimer.start();
					// Get the occupant details
					int occupantIndex = occupantList.getSelectedIndex();
					int occupantId = occupants.get(occupantIndex).getId();
					String occupantName = occupants.get(occupantIndex).getName();
					int occupantFloor = occupants.get(occupantIndex).getRoomFloor();
					int roomNumber = occupants.get(occupantIndex).getRoomNumber();
					// Get the elevator details
					int elevatorIndex = 0;
					int elevatorId = 1;
					int currentFloor = elevators.get(elevatorIndex).getCurrentFLoor();
					int destinationFloor = elevators.get(elevatorIndex).getDestinationFloor();
					// Sets the travel direction
					int travelDirection;
					if (currentFloor < occupantFloor) {
						travelDirection = 0;
					} else {
						travelDirection = 1;
					}
					int amountOfPeople = elevators.get(elevatorIndex).getCurrentCapacity();
					//If the people in elevator array list doesn't contain the occupantId add it to the list
					if(!peopleInElevator.contains(occupantId)) {
						peopleInElevator.add(occupantId);
					}
					int lowestFloor = 0;
					int highestFloor = 10;
					int capacityLimit = elevators.get(elevatorIndex).getCapacityLimit();
					boolean isMoving = elevators.get(elevatorIndex).getIsMoving();	
					// Create Request if the occupant hasn't already requested the elevator(This avoids duplicate requests that would increase the people count)
						requestObserver.onNext(ElevatorRequest.newBuilder()
							// Set the Occupant details(Id, name, floor, room number)
							.setOccupant(Occupant.newBuilder().setId(occupantId).setName(occupantName)
							.setRoomFloor(occupantFloor).setRoomNumber(roomNumber)
						.build())
							// Set the elevator details
							.setElevator(Elevator.newBuilder().setId(elevatorId).setCurrentFloor(currentFloor)
							.setDestinationFLoor(destinationFloor).setLowestFloor(lowestFloor)
							.setHighestFloor(highestFloor).setCurrentCapacity(++amountOfPeople)
							.setCapacityLimit(capacityLimit).setIsMoving(isMoving)
							.setTDirectionValue(travelDirection))
						.build());
						serverResponse.append("Client Request made by occupant ID " + occupantId + ": "+ occupantName + "\n");
					//Update the elevator instances amountOfPeople variable
					elevators.get(elevatorIndex).setCurrentCapactity(peopleInElevator.size());
				}catch(RuntimeException err) {
					err.printStackTrace();
				}
			}
		});

		serviceSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(initialiseStartPanel());
				// Visibility Setting below is so the JFrame can load the selected options panel
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});
		return panel;
	}
}

