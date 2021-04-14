package grpc.elevatorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import grpc.elevatorService.Elevator.travelDirection;
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
				int eOnePeopleCount, eTwoPeopleCount, eThreePeopleCount; // this will keep track of how many people get into each elevator
				int eOneCurrentFloor = 0;
				int eTwoCurrentFloor = 0;
				int eThreeCurrentFloor = 0;
				int destinationFloor;
				String direction;
				ArrayList<Integer> eOneDestinationFloors = new ArrayList<>(); // create an arraylist to store the
																				// destination floors for elevator 1
				ArrayList<Integer> eTwoDestinationFloors = new ArrayList<>(); // create an arraylist to store the
																				// destination floors for elevator 2
				ArrayList<Integer> eThreeDestinationFloors = new ArrayList<>(); // create an arraylist to store the
																				// destination floors for elevator 3

				@Override
				public void onNext(ElevatorRequest value) {
					int elevatorId = value.getElevator().getId();
					// if the current floor is the occupants floor they are wanting to go to floor 0
					if (value.getOccupant().getRoomFloor() == value.getElevator().getCurrentFloor()) {
						destinationFloor = 0;
						direction = "down";
					} else {
						destinationFloor = value.getOccupant().getRoomFloor();
					}
					// If the elevator in use is elevatorId 1
					if (elevatorId == 1) {
						eOnePeopleCount++; // Increase the people count for every new person to get into the elevator
						// If the elevator has too many people in it (>8)
						if (eOnePeopleCount > value.getElevator().getCapacityLimit()) {
							System.out.println("Elevator 1 has " + eOnePeopleCount
									+ " people in it. Maximum capacity the elevator can accept is "
									+ value.getElevator().getCapacityLimit());
							// If the amount of people in the elevator is below the capacity limit the
							// elevator can travel
						} else {
							// Set the elevators direction
							if (value.getElevator().getTDirection() == travelDirection.UP) {
								direction = "up";
							} else if (value.getElevator().getTDirection() == travelDirection.DOWN) {
								direction = "down";
							}

							System.out.println("Receiving Elevator Request from occupant id "
									+ value.getOccupant().getId() + " to go from floor "
									+ value.getElevator().getCurrentFloor() + " " + direction + " to floor "
									+ destinationFloor + " using elevator " + elevatorId);

							// Add the destination floor to the floors arrayList if it isn't in the list
							if (!eOneDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
								eOneDestinationFloors.add(value.getOccupant().getRoomFloor());
								System.out.println("Floor " + value.getOccupant().getRoomFloor()
										+ " added to list of floors elevator 1 is travelling to");
							}

							// Create response for the amount of people in the elevator
							ElevatorResponse response = ElevatorResponse.newBuilder()
									.setElevatorMessage("Received request from occupant id: "
											+ value.getOccupant().getId() + ". Request: Go from floor "
											+ value.getElevator().getCurrentFloor() + " " + direction + " to floor "
											+ destinationFloor + " using elevator " +  value.getElevator().getId())
									.setNextFloor(eOneDestinationFloors.get(0)).setCurrentFloor(eOneCurrentFloor).build();
							responseObserver.onNext(response);
						}
					}

					// If the elevator in use is elevatorId 2
					if (elevatorId == 2) {
						eTwoPeopleCount++; // Increase the people count for every new person to get into the elevator
						// If the elevator has too many people in it (>8)
						if (eTwoPeopleCount > value.getElevator().getCapacityLimit()) {
							System.out.println("Elevator 2 has " + eTwoPeopleCount
									+ " people in it. Maximum capacity the elevator can accept is "
									+ value.getElevator().getCapacityLimit());
							// If the amount of people in the elevator is below the capacity limit the
							// elevator can travel
						} else {
							//Show the elevator request details
							System.out.println("Receiving Elevator Request from occupant id "
									+ value.getOccupant().getId() + " to go from floor "
									+ value.getElevator().getCurrentFloor() + " " + direction + " to floor "
									+ destinationFloor + " using elevator " + elevatorId);

							// Add the destination floor to the floors arrayList if it isn't in the list
							if (!eTwoDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
								eTwoDestinationFloors.add(value.getOccupant().getRoomFloor());
								System.out.println("Floor " + value.getOccupant().getRoomFloor()
										+ " added to list of floors elevator 2 is travelling to");
							}

							// Create response for the amount of people in the elevator
							ElevatorResponse response = ElevatorResponse.newBuilder()
									.setElevatorMessage("Received request from occupant id: "
											+ value.getOccupant().getId() + ". Request: Go from floor "
											+ value.getElevator().getCurrentFloor() + " " + direction + " to floor "
											+ destinationFloor + " using elevator " + value.getElevator().getId())
									.setNextFloor(eOneDestinationFloors.get(0)).setCurrentFloor(eTwoCurrentFloor).build();
							responseObserver.onNext(response);
						}
					}
					// If the elevator in use is elevatorId 3
					if (elevatorId == 3) {
						eThreePeopleCount++; // Increase the people count for every new person to get into the elevator
						// If the elevator has too many people in it (>8)
						if (eThreePeopleCount > value.getElevator().getCapacityLimit()) {
							System.out.println("Elevator 3 has " + eThreePeopleCount
									+ " people in it. Maximum capacity the elevator can accept is "
									+ value.getElevator().getCapacityLimit());
							// If the amount of people in the elevator is below the capacity limit the
							// elevator can travel
						} else {
							// Set the elevators direction
							if (value.getElevator().getTDirection() == travelDirection.UP) {
								direction = "up";
							} else if (value.getElevator().getTDirection() == travelDirection.DOWN) {
								direction = "down";
							}

							System.out.println("Receiving Elevator Request from occupant id "
									+ value.getOccupant().getId() + " to go from floor "
									+ value.getElevator().getCurrentFloor() + " " + direction + " to floor "
									+ destinationFloor + " using elevator " + elevatorId);

							// Add the destination floor to the floors arrayList if it isn't in the list
							if (!eThreeDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
								eThreeDestinationFloors.add(value.getOccupant().getRoomFloor());
								System.out.println("Floor " + value.getOccupant().getRoomFloor()
										+ " added to list of floors elevator 3 is travelling to");
							}

							// Create response for the amount of people in the elevator
							ElevatorResponse response = ElevatorResponse.newBuilder()
									.setElevatorMessage("Received request from occupant id: "
											+ value.getOccupant().getId() + ". Request: Go from floor "
											+ value.getElevator().getCurrentFloor() + " " + direction + " to floor "
											+ destinationFloor + " using elevator " + value.getElevator().getId())
									.setNextFloor(eOneDestinationFloors.get(0)).setCurrentFloor(eThreeCurrentFloor).build();
							responseObserver.onNext(response);
						}
					}
				}

				@Override
				public void onError(Throwable t) {

				}

				@Override
				public void onCompleted() {
					int eOneResponseCount = 0;	//elevator one response count
					int eTwoResponseCount = 0;	//elevator two response count
					int eThreeResponseCount = 0;	//elevator three response count
					// Build responses for as many floors as there in the eOneDestinationFloors array list
					do {
						int nextFloor = eOneDestinationFloors.get(0);
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Elevator 1 going from floor " + eOneCurrentFloor + " " + direction
										+ " to floor " + eOneDestinationFloors.remove(0) + ". Next floor(s): "
										+ eOneDestinationFloors)
								.setNextFloor(nextFloor).setCurrentFloor(eOneCurrentFloor).build();
						// assign the currentFLoor to the responses destination floor
						eOneCurrentFloor = nextFloor;
						responseObserver.onNext(response);
					} while (eOneResponseCount < eOneDestinationFloors.size());
					// Build responses for as many floors as there in the eOneDestinationFloors array list
					do {
						int nextFloor = eTwoDestinationFloors.get(0);
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Elevator 2 going from floor " + eTwoCurrentFloor + " " + direction
										+ " to floor " + eTwoDestinationFloors.remove(0) + ". Next floor(s): "
										+ eTwoDestinationFloors)
								.setNextFloor(nextFloor).setCurrentFloor(eTwoCurrentFloor).build();
						// assign the currentFLoor to the responses destination floor
						eTwoCurrentFloor = nextFloor;
						responseObserver.onNext(response);
					} while (eTwoResponseCount < eTwoDestinationFloors.size());
					do {
						int nextFloor = eThreeDestinationFloors.get(0);
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Elevator 3 going from floor " + eThreeCurrentFloor + " " + direction
										+ " to floor " + eThreeDestinationFloors.remove(0) + ". Next floor(s): "
										+ eThreeDestinationFloors)
								.setNextFloor(nextFloor).setCurrentFloor(eThreeCurrentFloor).build();
						// assign the currentFLoor to the responses destination floor
						eThreeCurrentFloor = nextFloor;
						responseObserver.onNext(response);
					} while (eThreeResponseCount < eThreeDestinationFloors.size());
					
					responseObserver.onCompleted();
				}
			};
		}

		@Override
		public void returnToGroundFloor(Elevator request, StreamObserver<ElevatorResponse> responseObserver) {

		}

	}

}