package grpc.elevatorService;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import grpc.elevatorService.Elevator;
import grpc.elevatorService.ElevatorDb;
import grpc.elevatorService.ElevatorRequest;
import grpc.elevatorService.ElevatorResponse;
import grpc.elevatorService.Occupant;
import grpc.elevatorService.OccupantDb;
import grpc.elevatorService.elevatorGrpc;
import grpc.elevatorService.elevatorGrpc.elevatorBlockingStub;
import grpc.elevatorService.elevatorGrpc.elevatorStub;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.awt.EventQueue;

public class ElevatorClient {
	private static elevatorBlockingStub blockingStub;
	private static elevatorStub asyncStub;
	
	// This arraylist will contain the database for the occupants
	static ArrayList<OccupantDb> occupants = new ArrayList<>();

	// This arraylist will contain the elevator details
	static ArrayList<ElevatorDb> elevators = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		final Logger logger = Logger.getLogger(ElevatorClient.class.getName());
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();
		//stubs -- generate from proto
		blockingStub = elevatorGrpc.newBlockingStub(channel);

		asyncStub = elevatorGrpc.newStub(channel);
		// Get the occupant data from the occupantData CSV file and create Occupant
		// records with it
		Scanner sc = new Scanner(new File("src/main/resources/occupantData.csv"));
		// create the arraylist that will be used for storing the database info
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
		// Create Elevator instances (3 elevators)
		elevators.add(0, new ElevatorDb(1, 0, 0, 0, false));
		elevators.add(0, new ElevatorDb(2, 0, 0, 0, false));
		elevators.add(0, new ElevatorDb(3, 0, 0, 0, false));

		moveElevator();
	}

	// A search method to make sure the correct occupant record is being read to
	// retrieve their info with
	public static int binarySearch(ArrayList<OccupantDb> arr, int start, int end, int searchKey) {
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

	// A search method to make sure the correct elevator record is being read to
	// retrieve info with. Only 3 elevators so Linear search should be fast enough
	public static int linearSearch(ArrayList<ElevatorDb> arr, int searchKey) {
		// traverse the elevators array list
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).getId() == searchKey) {
				return i;
			}
		}
		return -1;
	}

	// Methods from server
	public static void moveElevator() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What elevator number are you using?");
		int elevatorId = sc.nextInt();
		System.out.println("How many people are using the elevator?");
		int peopleAmount = sc.nextInt();
		//Create an array of people size
		int[]occupantAmount = new int[peopleAmount];
		int occupantId = 0;
		for(int i=0;i<occupantAmount.length;i++) {
			System.out.println("Person " + (i+1) + ". What is your occupant id?");
			occupantId = sc.nextInt();
			occupantAmount[i] = occupantId;
		}
		sc.close();
		// Assign the Occupant values based on the occupant Id
		for(int i=0;i<occupantAmount.length;i++) {
			int occupantIndex = binarySearch(occupants, 0, occupants.size() - 1, occupantAmount[i]);
			int occupantFloor = occupants.get(occupantIndex).getRoomFloor();
			int roomNumber = occupants.get(occupantIndex).getRoomNumber();
			String occupantName = occupants.get(occupantIndex).getName();
			// Assign the Elevator values based on the elevator id
				int elevatorIndex = linearSearch(elevators, elevatorId);
				int currentFloor = elevators.get(elevatorIndex).getCurrentFLoor();
				int destinationFloor = elevators.get(elevatorIndex).getDestinationFloor();
				int lowestFloor = 0;
				int highestFloor = 10;
				int capacityLimit = 8;
				boolean isMoving = elevators.get(elevatorIndex).getIsMoving();
				int tDirection = 3;
				// if the elevator is moving and the current floor is below the destination floor the travel direction is up
				if (elevators.get(elevatorIndex).getIsMoving() && elevators.get(elevatorIndex).getCurrentFLoor() < elevators.get(elevatorIndex).getDestinationFloor()) {
					tDirection = 0;
					// if the elevator is moving and the current floor is above the destination floor the travel direction is down
				} else if (elevators.get(elevatorIndex).getIsMoving() && elevators.get(elevatorIndex).getCurrentFLoor() > elevators.get(elevatorIndex).getDestinationFloor()) {
					tDirection = 1;
				}else {
					tDirection = 3;
				}
		StreamObserver<ElevatorResponse> responseObserver = new StreamObserver<ElevatorResponse>() {

			int requestAmount;

			@Override
			public void onNext(ElevatorResponse response) {
				System.out.println(response.getElevatorMessage());
				requestAmount++;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Elevator has finished its journey and is currently at floor.");
			}
		};

		StreamObserver<ElevatorRequest> requestObserver = asyncStub.moveElevator(responseObserver);
		
				try {
					// set the occupant details
					requestObserver.onNext(ElevatorRequest.newBuilder()
					// Set the Occupant details(Id, name, floor, room number)
							.setOccupant(Occupant.newBuilder().setId(occupantId).setName(occupantName).setRoomFloor(occupantFloor)
							.setRoomNumber(roomNumber).build())
					// Set the elevator details(Id,currentfloor)
							.setElevator(Elevator.newBuilder().setId(elevatorId).setCurrentFloor(currentFloor)
							.setDestinationFLoor(destinationFloor).setLowestFloor(lowestFloor).setHighestFloor(highestFloor)
							.setCapacityLimit(capacityLimit).setIsMoving(isMoving).setTDirectionValue(tDirection))
					.build());
					
					//Mark the end of requests
					requestObserver.onCompleted();
					
					//sleep for a bit
					Thread.sleep(1000);
				}catch(RuntimeException e) {
					e.printStackTrace();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}
