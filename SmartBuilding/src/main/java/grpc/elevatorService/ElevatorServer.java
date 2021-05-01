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

			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			int service_port = Integer.valueOf(prop.getProperty("service_port"));
			String service_description_properties = prop.getProperty("service_description");

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
			int currentFloor = 0;
			int destinationFloor;
			String direction;
			int occupantCounter;
			// create an array list to store the destination floors for elevator
			ArrayList<Integer> eOneDestinationFloors = new ArrayList<>(); 


			@Override
			public void onNext(ElevatorRequest value) {
				occupantCounter = value.getElevator().getCurrentCapacity();
				int tester = 0;
				int elevatorId = value.getElevator().getId();
				System.out.println("Elevator ID: " + elevatorId);
				// if the current floor is the occupants floor they are wanting to go to floor 0
				if (value.getOccupant().getRoomFloor() == value.getElevator().getCurrentFloor()) {
					destinationFloor = 0;
				} else {
					destinationFloor = value.getOccupant().getRoomFloor();
				}
				currentFloor = value.getElevator().getCurrentFloor();
				if (value.getElevator().getCurrentCapacity() <= value.getElevator().getCapacityLimit()) {
					System.out.println("Amount of people E1: " + value.getElevator().getCurrentCapacity());
					// Add the destination floor to the floors arrayList if it isn't in the list
					if (!eOneDestinationFloors.contains(value.getOccupant().getRoomFloor())) {
						eOneDestinationFloors.add(destinationFloor);
					}
					System.out.println("Floor requests: " + eOneDestinationFloors);
					ElevatorResponse response = ElevatorResponse.newBuilder()
							.setElevatorMessage("Received request from occupant id: " + value.getOccupant().getId()
							+ ". Request: Go from floor " + value.getElevator().getCurrentFloor() + " "
							+ value.getElevator().getTDirection() + " to floor " + destinationFloor + " using elevator "
							+ value.getElevator().getId() + ". Floor Requests " + eOneDestinationFloors)
							.setNextFloor(eOneDestinationFloors.get(0)).setCurrentFloor(currentFloor).build();
					responseObserver.onNext(response);
				} else {
					//If there are too many people in the elevator(>8)
					ElevatorResponse response = ElevatorResponse.newBuilder()
							.setElevatorMessage("Elevator has " + value.getElevator().getCurrentCapacity()
							+ " people in it. Maximum capacity the elevator can accept is "
							+ value.getElevator().getCapacityLimit())
					.build();
					responseObserver.onNext(response);
				}
			}
			
			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {
				//If there are too many people in the elevator	
				if(occupantCounter >=8) {
					ElevatorResponse response = ElevatorResponse.newBuilder()
							.setElevatorMessage("There are too many people in the elevator. Maximum the elevator can take is 8. There is currently " + (occupantCounter-8) +
									" person(s) too many").build();
					responseObserver.onNext(response);
				}else {
					// Sort the array list so the elevator stops for each floor as it is travelling up or down
					Collections.sort(eOneDestinationFloors);
						int floorDifference;
						int responseTime;
						// Build responses for as many floors as there in the eOneDestinationFloors
						do {
							if(currentFloor < eOneDestinationFloors.get(0)) {
								direction = "UP";
								floorDifference = eOneDestinationFloors.get(0) - currentFloor;
								
							}else {
								direction = "DOWN";
								floorDifference = currentFloor - eOneDestinationFloors.get(0);
							}
							//This switch statement controls how much time is passed before the server returns a response as it will take time for the elevator to travel
							switch(floorDifference) {
							case 1:
								responseTime = 4000;
							break;
							case 2:
								responseTime = 8000;
							break;
							case 3:
								responseTime = 12000;
							break;
							case 4:
								responseTime = 16000;
							break;
							case 5:
								responseTime = 20000;
							break;
							case 6:
								responseTime = 24000;
							break;
							case 7:
								responseTime = 28000;
							break;
							case 8:
								responseTime = 32000;
							break;
							case 9:
								responseTime = 36000;
							break;
							default: 
								responseTime = 0;
							}
							int nextFloor = eOneDestinationFloors.get(0);
							//If there are 2 or more destination floors left for the elevator
							if(eOneDestinationFloors.size()>=2) {
								ElevatorResponse response = ElevatorResponse.newBuilder()
										.setElevatorMessage("Elevator going from floor " + currentFloor + " " + direction
										+ " to floor " + eOneDestinationFloors.remove(0) + ". Next floor(s): "
										+ eOneDestinationFloors)
										.setNextFloor(nextFloor).setCurrentFloor(currentFloor).build();
								currentFloor = nextFloor;
								responseObserver.onNext(response);
							}else {
								//There is only one floor remaining in the elevator journey for this repsonse so the array list of the floors remaining isn't shown.
								destinationFloor = eOneDestinationFloors.remove(0);
								ElevatorResponse response = ElevatorResponse.newBuilder()
										.setElevatorMessage("Elevator going from floor " + currentFloor + " " + direction
												+ " to floor " + destinationFloor)
										.setCurrentFloor(destinationFloor).build();
								currentFloor = destinationFloor;
								responseObserver.onNext(response);
								}
							// assign the currentFLoor to the responses destination floor
							ElevatorResponse responseTwo = ElevatorResponse.newBuilder()
									.setElevatorMessage("Elevator has reached floor " + currentFloor).setCurrentFloor(currentFloor).build();
	
							try {
								Thread.sleep(responseTime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							responseObserver.onNext(responseTwo);
						} while (!eOneDestinationFloors.isEmpty());
					
					responseObserver.onCompleted();
				}
			}
		};
	}
}
