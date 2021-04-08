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
	
	//server variables
	private final int PORT = 50051;
	private static final Logger LOGGER = Logger.getLogger(ElevatorServer.class.getName());
	private Server server;
		
	//MAIN METHOD
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Instantiate the elevator server class
		final ElevatorServer elevatorServer = new ElevatorServer();
		//Get the database and create OccupantDb records with i
		JsonObject object = (JsonObject) new JsonParser().parse(new FileReader("src/main/resources/smartBuildingDb.json"));
        JsonArray occupantsJson = (JsonArray) object.get("occupant");
        ArrayList<Object> occupants = new ArrayList<>(occupantsJson.size());

        //loops through the Json data and assigns each occupant their own OccuppantDb instance
        for(int i=0; i<occupantsJson.size(); i++){
        	occupants.add(i, occupantsJson.get(i));
        }

		
		elevatorServer.start();
		elevatorServer.blockUntilShutdown();
	}
	
	//START serving requests
	public void start() throws IOException {
		//initialise server
		server = ServerBuilder.forPort(PORT).addService(new ElevatorImpl()).build().start();
		//show what number port the server has started listening on
		LOGGER.info("***SERVER STARTED LISTENING ON PORT: " + PORT + "***");
		//If the server shuts down give some information on why
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.println("***SHUTTING DOWN SERVER BECAUSE JVM IS SHUTTING DOWN***");
				try {
					ElevatorServer.this.stop();
				} catch(InterruptedException e) {
					e.printStackTrace(System.err);
				}
				System.err.println("***SERVER SHUT DOWN***");
			}
		});
	}
	
	//STOP serving requests
	private void stop() throws InterruptedException{
		if(server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}
	
	//AWAIT termination
	private void blockUntilShutdown() throws InterruptedException{
		if (server != null) {
			server.awaitTermination();
		}
	}
	

	
	static class ElevatorImpl extends elevatorGrpc.elevatorImplBase{

		@Override
		public StreamObserver<ElevatorRequest> moveElevator( final StreamObserver<ElevatorResponse> responseObserver) {
			return new StreamObserver<ElevatorRequest>() {
				final int CAPACITYLIMIT = 10;	//elevator capacity is 10 people at a time
				int peopleCount = 0;	//this will keep track of how many people get into the elevator
				int currentFloor;
				//int destinationFloor
					
		
				@Override
				public void onNext(ElevatorRequest value) {
					peopleCount++;
					Occupant occupant = value.getOccupant();
					
					
					
				}
				@Override
				public void onError(Throwable t) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void onCompleted() {
					// TODO Auto-generated method stub
					
				}
				
			};
		}

		@Override
		public void returnToGroundFloor(Elevator request, StreamObserver<ElevatorResponse> responseObserver) {
			
		}
		
	}
	
	
	

}