package grpc.elevatorService;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ElevatorClient {
	private static final Logger logger = Logger.getLogger(ElevatorClient.class.getName());

	// Create the stubs
	private elevatorGrpc.elevatorBlockingStub blockingStub;
	private static elevatorGrpc.elevatorStub asyncStub;

	// Create client constructor
	public ElevatorClient(Channel channel) {
		this.blockingStub = elevatorGrpc.newBlockingStub(channel);
		asyncStub = elevatorGrpc.newStub(channel);
	}
	
	//This arraylist will contain the database for the occupants
	static ArrayList<OccupantDb> occupants = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		// Get the database and create OccupantDb records with it
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
		moveElevator();
	}

	//A search method to make sure the correct occupant record is being read to retrieve their info with
	public static int binarySearch(ArrayList<OccupantDb> arr, int start, int end, int searchKey) {
		// if the end index of the array list is equal to or higher than the starting index
		int middle = 0;
		if (end >= start) {
			// get the middle index of the array
			middle = start + (end - start) / 2;
			// if the middle index value is the search key(Occupant Id) return that index
			if (arr.get(middle).getId() == searchKey) {
				return middle;
				//if the middle index is greater than the search key recursively call the method
			} else if (arr.get(middle).getId()> searchKey) {
				return binarySearch(arr, start, middle - 1, searchKey);
				//if the middle index is less than the search key recursively call the method
			} else if (arr.get(middle).getId()< searchKey) {
				return binarySearch(arr, middle + 1, end, searchKey);
			}
		}
		return middle;
	}

	//Methods from server
	public static void moveElevator() {
		 
		StreamObserver<ElevatorResponse> responseObserver = new StreamObserver<ElevatorResponse>() {
			
			int currentFloor;
			@Override
			public void onNext(ElevatorResponse response) {
				System.out.println(response.getElevatorMessage());
				currentFloor = response.getNextFloor();
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				System.out.println("Elevator currently at floor: " + currentFloor);
			}	
		};
		
		StreamObserver<ElevatorRequest> requestObserver = asyncStub.moveElevator(responseObserver);
		
		//send in the occupant details
//		try {
//			int occupantIndex = binarySearch(occupants, 0, occupants.size()-1, 95);
//			requestObserver.onNext(ElevatorRequest.newBuilder().setOccupant(occupants.get(occupantIndex)).build();
//		}
	}

}
