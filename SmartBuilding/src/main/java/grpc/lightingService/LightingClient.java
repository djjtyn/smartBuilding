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
		//adjustLighting();
		adjustLightingMultiRoom();
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
	
	public static void adjustLightingMultiRoom() {
		StreamObserver<LightingResponse> responseObserver = new StreamObserver<LightingResponse>() {

			@Override
			public void onNext(LightingResponse value) {
				System.out.println("Can I reach here?");
				System.out.println(value.getLightingMessage());
				
			}

			@Override
			public void onError(Throwable t) {
				
			}

			@Override
			public void onCompleted() {
				System.out.println("Finished adjusting lighting");
				
			}
			
		};
		
		StreamObserver<Room> requestObserver = asyncStub.adjustLightingMultiRoom(responseObserver);
		//create requestCount to count requests being sent
		int roomId = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("How many rooms are you adjusting the lighting for?");
		int roomAmount = sc.nextInt();
		//Create an array using the roomAmount
		int[] selectedRooms = new int[roomAmount];
		int requestAmount = 1;
		//Fill the selectedRooms with room id's
		while(requestAmount <= roomAmount) {
			int i =0;
			//Get the room id's of all the rooms the user wants to adjust the brightness for
			System.out.println("What is the id of room " + requestAmount + ":");
			roomId = sc.nextInt();
			selectedRooms[i] = roomId;
			int roomIndex = binarySearch(rooms, 0, rooms.size() - 1, selectedRooms[i]);
			//Reassign the room id for each selected room
			roomId = rooms.get(roomIndex).getId();
			int currentBrightness = rooms.get(roomIndex).getBrightness();
			String roomName = rooms.get(roomIndex).getRoomName();
			System.out.println("What percentage do you want to set the brightness for Room: " + roomName);
			int desiredBrightness = sc.nextInt();
			requestAmount++;
			//Create a try/catch block to set the request details using the info gathered from the for loop
			try {
				requestObserver.onNext(Room.newBuilder().setBrightness(currentBrightness).setId(roomId).setRoomName(roomName).setIntAdjust(desiredBrightness).build());
				//REQUEST OBSERVER NOT WORKING YET!!!
			}catch(RuntimeException e) {
				e.printStackTrace();
			}
		}
		requestObserver.onCompleted();
		}
	}


