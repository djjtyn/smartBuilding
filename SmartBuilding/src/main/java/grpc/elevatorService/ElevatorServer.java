package grpc.elevatorService;

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
				int peopleCount; // this will keep track of how many people get into the elevator
				int currentFloor = 0;
				int destinationFloor;
				String elevatorDetails;
				ArrayList<Integer> eOneDestinationFloors = new ArrayList<>(); // create an arraylist to store the destination floors for elevator 1
				ArrayList<Integer> eTwoDestinationFloors = new ArrayList<>(); // create an arraylist to store the destination floors for elevator 2
				ArrayList<Integer> eThreeDestinationFloors = new ArrayList<>(); // create an arraylist to store the destination floors for elevator 3
				@Override
				public void onNext(ElevatorRequest value) {
					int elevatorId = value.getElevator().getId();
					// If the elevator in use is elevatorId 1
					if (elevatorId == 1) {
						peopleCount++; // Increase the people count for every new person to get into the elevator
						// If the elevator has too many people in it (>8)
						if (peopleCount > value.getElevator().getCapacityLimit()) {
							System.out.println("Elevator has " + peopleCount
									+ " people in it. Maximum capacity the elevator can accept is "
									+ value.getElevator().getCapacityLimit());
						} else {
							System.out.println(
									"Receiving Elevator Request from occupant id " + value.getOccupant().getId()
											+ " to go from " + value.getElevator().getTDirectionValue() + " to floor "
											+ value.getOccupant().getRoomFloor() + " using elevator id " + elevatorId);
						}
						// Add the destination floor to the floors arrayList if it isn't in the list
						if (!eOneDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
							eOneDestinationFloors.add(value.getOccupant().getRoomFloor());
						}
							ElevatorResponse response = ElevatorResponse.newBuilder()
									.setElevatorMessage("Elevator 1 going " + value.getElevator().getTDirectionValue() + " to floor(s) " + 
							eOneDestinationFloors + ". Next floor: " + eOneDestinationFloors.get(0))
									.setNextFloor(destinationFloor).setCurrentFloor(currentFloor).build();
							responseObserver.onNext(response);
							currentFloor = destinationFloor; // change the currentFloor value to the occupants
																// destination floor
						}
						if (elevatorId == 2) {
							if (!eTwoDestinationFloors.contains(destinationFloor)) {
									eTwoDestinationFloors.add(destinationFloor);
							}
									ElevatorResponse response2 = ElevatorResponse.newBuilder()
											.setElevatorMessage("Elevator 2 going to floor(s) " + eTwoDestinationFloors
													+ ". Next floor: " + destinationFloor)
											.setNextFloor(destinationFloor).setCurrentFloor(currentFloor).build();
									responseObserver.onNext(response2);
									currentFloor = destinationFloor; // change the currentFloor value to the occupants destination floor
						if (elevatorId == 3) {
							if (!eThreeDestinationFloors.contains(destinationFloor)) {
								eThreeDestinationFloors.add(destinationFloor);
							}
							ElevatorResponse response3 = ElevatorResponse.newBuilder()
									.setElevatorMessage("Elevator 3 going to floor(s) " + eThreeDestinationFloors
											+ ". Next floor: " + destinationFloor)
									.setNextFloor(destinationFloor).setCurrentFloor(currentFloor).build();
							responseObserver.onNext(response3);
							currentFloor = destinationFloor; // change the currentFloor value to the occupants
																// destination floor
						}
					}
				}

				@Override
				public void onError(Throwable t) {

				}

				@Override
				public void onCompleted() {
					ElevatorResponse response = ElevatorResponse.newBuilder()
							.setElevatorMessage("Elevator 1 going to floor(s) " + eOneDestinationFloors
									+ ". Next floor: " + destinationFloor)
							.setNextFloor(destinationFloor).setCurrentFloor(currentFloor).build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();

				}

			};
		}

		@Override
		public void returnToGroundFloor(Elevator request, StreamObserver<ElevatorResponse> responseObserver) {

		}

	}

}