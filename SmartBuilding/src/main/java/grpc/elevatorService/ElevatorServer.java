package grpc.elevatorService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ElevatorServer {

	// server variables
	private final int PORT = 50051;
	private static final Logger LOGGER = Logger.getLogger(ElevatorServer.class.getName());
	private Server server;

	// MAIN METHOD
	public static void main(String[] args) throws IOException, InterruptedException {

		// Instantiate the elevator server class
		final ElevatorServer elevatorServer = new ElevatorServer();
	

		elevatorServer.start();
		elevatorServer.blockUntilShutdown();
	}

	// START serving requests
	public void start() throws IOException {
		// initialise server
		server = ServerBuilder.forPort(PORT).addService(new ElevatorImpl()).build().start();
		// show what number port the server has started listening on
		LOGGER.info("***SERVER STARTED LISTENING ON PORT: " + PORT + "***");
		// If the server shuts down give some information on why
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.println("***SHUTTING DOWN SERVER BECAUSE JVM IS SHUTTING DOWN***");
				try {
					ElevatorServer.this.stop();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
				System.err.println("***SERVER SHUT DOWN***");
			}
		});
	}

	// STOP serving requests
	private void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	// AWAIT termination
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	static class ElevatorImpl extends elevatorGrpc.elevatorImplBase {

		@Override
		public StreamObserver<ElevatorRequest> moveElevator(final StreamObserver<ElevatorResponse> responseObserver) {
			return new StreamObserver<ElevatorRequest>() {
				final int CAPACITYLIMIT = 10; // elevator capacity is 10 people at a time
				int peopleCount = 0; // this will keep track of how many people get into the elevator
				int currentFloor = 0;
				int occupantId;
				int destinationFloor;
				String elevatorDetails;
				ArrayList<Integer> destinationFloors = new ArrayList<>();	//create an arraylist to store the destination floors

				@Override
				public void onNext(ElevatorRequest value) {
					peopleCount++; // Increase the people count for every new person to get into the elevator
					int occupantId = value.getOccupant().getId();
					 destinationFloor = value.getOccupant().getRoomFloor();
					 //Add the destination floor to the floors arrayList if it isn't in the list
					 if(!destinationFloors.contains(destinationFloor)) {
						 destinationFloors.add(destinationFloor);
							ElevatorResponse response = ElevatorResponse.newBuilder()
									.setElevatorMessage("Elevator going to floor(s) " + destinationFloors + ". Next floor: " + destinationFloor)
									.setNextFloor(destinationFloor).setCurrentFloor(currentFloor).build();
							responseObserver.onNext(response);
							currentFloor = destinationFloor;	//change the currentFloor value to the occupants destination floor
	
					 }
					//if the destinationFloors array only has one instance of the users destination floor

					//if there is a rquest for afloor that already exists
				}

				@Override
				public void onError(Throwable t) {

				}

				@Override
				public void onCompleted() {
					for(int i = 0;i<peopleCount;i++) {
						
					}

				}

			};
		}

		@Override
		public void returnToGroundFloor(Elevator request, StreamObserver<ElevatorResponse> responseObserver) {

		}

	}

}