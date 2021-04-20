package grpc.elevatorService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/*The imports commented out are not being used currently but may be used for database data at a later time
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
 * 
 */

import grpc.elevatorService.Elevator.travelDirection;
import grpc.elevatorService.elevatorGrpc.elevatorImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ElevatorServer extends elevatorImplBase {

	// Method to get properties from the occupant.properties file
	private Properties getProperties() {

		Properties prop = null;
		String dir = System.getProperty("user.dir"); // Get the users current directory to be used with file location
														// below
		try (InputStream input = new FileInputStream(dir + "/src/main/resources/elevatorService/elevator.properties")) {
			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Elevator Service properies ...");
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	// Method to register service to OccupantServer to be used with Server instance
	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String service_type = prop.getProperty("service_type");// "_http._tcp.local.";
			String service_name = prop.getProperty("service_name");// "example";
			int service_port = Integer.valueOf(prop.getProperty("service_port"));// #.50051;
			String service_description_properties = prop.getProperty("service_description");// "path=index.html";

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);
			System.out.printf("Registering service with type %s and name %s \n", service_type, service_name);

			// Wait a bit
			Thread.sleep(1000);

			// Unregister all services
			// jmdns.unregisterAllServices();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// MAIN METHOD
	public static void main(String[] args) throws IOException, InterruptedException {

		// Instantiate the elevator server class
		final ElevatorServer elevatorServer = new ElevatorServer();

		// Get the properties info
		Properties prop = elevatorServer.getProperties();

		// Register the service
		elevatorServer.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {

			Server server = ServerBuilder.forPort(port).addService(elevatorServer).build().start();

			System.out.println("Elevator Server started listening on " + port);

			server.awaitTermination();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void returnToGroundFloor(Elevator request, StreamObserver<ElevatorResponse> responseObserver) {
		// If elevator is already on the ground floor
		if (request.getCurrentFloor() == 0) {
			System.out.println("Elevator " + request.getId() + " is already on the ground floor");
			ElevatorResponse response = ElevatorResponse.newBuilder().setCurrentFloor(request.getCurrentFloor())
					.setNextFloor(request.getCurrentFloor())
					.setElevatorMessage("Elevator is already on the ground floor.").build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} else {
			System.out.println("Elevator " + request.getId() + " is currently on floor " + request.getCurrentFloor()
					+ " and will travel to ground floor");
			ElevatorResponse response = ElevatorResponse.newBuilder().setCurrentFloor(request.getCurrentFloor())
					.setNextFloor(0)
					.setElevatorMessage(
							"Elevator is travelling from floor " + request.getCurrentFloor() + " to ground floor.")
					.build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
	}

	public StreamObserver<ElevatorRequest> moveElevator(final StreamObserver<ElevatorResponse> responseObserver) {
		return new StreamObserver<ElevatorRequest>() {
			int eOnePeopleCount, eTwoPeopleCount, eThreePeopleCount; // this will keep track of how many people get into
																		// each elevator
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

						System.out.println("Receiving Elevator Request from occupant id " + value.getOccupant().getId()
								+ " to go from floor " + value.getElevator().getCurrentFloor() + " " + direction
								+ " to floor " + destinationFloor + " using elevator " + elevatorId);

						// Add the destination floor to the floors arrayList if it isn't in the list
						if (!eOneDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
							eOneDestinationFloors.add(value.getOccupant().getRoomFloor());
							System.out.println("Floor " + value.getOccupant().getRoomFloor()
									+ " added to list of floors elevator 1 is travelling to");
						}

						// Create response for the amount of people in the elevator
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Received request from occupant id: " + value.getOccupant().getId()
										+ ". Request: Go from floor " + value.getElevator().getCurrentFloor() + " "
										+ direction + " to floor " + destinationFloor + " using elevator "
										+ value.getElevator().getId())
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
						// Show the elevator request details
						System.out.println("Receiving Elevator Request from occupant id " + value.getOccupant().getId()
								+ " to go from floor " + value.getElevator().getCurrentFloor() + " " + direction
								+ " to floor " + destinationFloor + " using elevator " + elevatorId);

						// Add the destination floor to the floors arrayList if it isn't in the list
						if (!eTwoDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
							eTwoDestinationFloors.add(value.getOccupant().getRoomFloor());
							System.out.println("Floor " + value.getOccupant().getRoomFloor()
									+ " added to list of floors elevator 2 is travelling to");
						}

						// Create response for the amount of people in the elevator
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Received request from occupant id: " + value.getOccupant().getId()
										+ ". Request: Go from floor " + value.getElevator().getCurrentFloor() + " "
										+ direction + " to floor " + destinationFloor + " using elevator "
										+ value.getElevator().getId())
								.setNextFloor(eTwoDestinationFloors.get(0)).setCurrentFloor(eTwoCurrentFloor).build();
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

						System.out.println("Receiving Elevator Request from occupant id " + value.getOccupant().getId()
								+ " to go from floor " + value.getElevator().getCurrentFloor() + " " + direction
								+ " to floor " + destinationFloor + " using elevator " + elevatorId);

						// Add the destination floor to the floors arrayList if it isn't in the list
						if (!eThreeDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
							eThreeDestinationFloors.add(value.getOccupant().getRoomFloor());
							System.out.println("Floor " + value.getOccupant().getRoomFloor()
									+ " added to list of floors elevator 3 is travelling to");
						}

						// Create response for the amount of people in the elevator
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Received request from occupant id: " + value.getOccupant().getId()
										+ ". Request: Go from floor " + value.getElevator().getCurrentFloor() + " "
										+ direction + " to floor " + destinationFloor + " using elevator "
										+ value.getElevator().getId())
								.setNextFloor(eThreeDestinationFloors.get(0)).setCurrentFloor(eThreeCurrentFloor)
								.build();
						responseObserver.onNext(response);
					}
				}
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {
				// Build responses for as many floors as there in the eOneDestinationFloors
				// array list
				if (eOneDestinationFloors.size() > 0) {
					// Sort the arraylist so the elevator stops for each floor as it is travelling
					// up or down
					Collections.sort(eOneDestinationFloors);
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
					} while (!eOneDestinationFloors.isEmpty());
				}
				// Build responses for as many floors as there in the eOneDestinationFloors
				// array list
				if (eTwoDestinationFloors.size() > 0) {
					// Sort the arraylist so the elevator stops for each floor as it is travelling
					// up or down
					Collections.sort(eTwoDestinationFloors);
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
					} while (!eTwoDestinationFloors.isEmpty());
				}
				if (eThreeDestinationFloors.size() > 0) {
					// Sort the arraylist so the elevator stops for each floor as it is travelling
					// up or down
					Collections.sort(eThreeDestinationFloors);
					do {
						int nextFloor = eThreeDestinationFloors.get(0);
						ElevatorResponse response = ElevatorResponse.newBuilder()
								.setElevatorMessage("Elevator 3 going from floor " + eThreeCurrentFloor + " "
										+ direction + " to floor " + eThreeDestinationFloors.remove(0)
										+ ". Next floor(s): " + eThreeDestinationFloors)
								.setNextFloor(nextFloor).setCurrentFloor(eThreeCurrentFloor).build();
						// assign the currentFLoor to the responses destination floor
						eThreeCurrentFloor = nextFloor;
						responseObserver.onNext(response);
					} while (!eThreeDestinationFloors.isEmpty());
				}

				responseObserver.onCompleted();
			}
		};
	}
}
