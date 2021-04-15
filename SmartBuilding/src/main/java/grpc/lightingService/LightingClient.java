package grpc.lightingService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import grpc.lightingService.lightingGrpc.lightingBlockingStub;
import grpc.lightingService.lightingGrpc.lightingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class LightingClient {
	
	private static lightingBlockingStub blockingStub;
	private static lightingStub asyncStub;
	
	// This arraylist will contain the database for the rooms
	static ArrayList<RoomDb> rooms = new ArrayList<>();
	
	public static void main(String[] args) {
		//Create some room records
		for(int i=0; i<30;i++) {
			rooms.add(i, new RoomDb((i+1), "Room " + (1 + i), 0, 0, 0.0));
		}
		
		final Logger logger = Logger.getLogger(LightingClient.class.getName());
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();
		//initialise the stubs 
		blockingStub = lightingGrpc.newBlockingStub(channel);
		asyncStub = lightingGrpc.newStub(channel);
		
		//Call the server methods
		adjustLighting();
	}
	
	// A search method to make sure the correct room record is being read to retrieve their info with
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
	
	//methods from server
	public static void adjustLighting() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What number room do you want to adjust the lighting for?");
		int roomSelect = sc.nextInt();
		System.out.println("What percentage do you want to set the lights at?");
		int lightAdjustment = sc.nextInt();
		//Get the index of the room number entered
		int roomIndex = binarySearch(rooms, 0, rooms.size()-1, roomSelect);
		int roomId = rooms.get(roomIndex).getId();
		String roomName = rooms.get(roomIndex).getRoomName();
		int currentBrightness = rooms.get(roomIndex).getBrightness();
		
		//Request
		Room request = Room.newBuilder().setBrightness(currentBrightness).setRoomName(roomName).setIntAdjust(lightAdjustment).build();
		
		//Response
		LightingResponse response = blockingStub.adjustLighting(request);
		
		//Set the rooms instance brightness value
		rooms.get(roomIndex).setBrightness(response.getBrightnessValue());
		
		System.out.println(response.getLightingMessage());
		
		
		
	}
	

}

