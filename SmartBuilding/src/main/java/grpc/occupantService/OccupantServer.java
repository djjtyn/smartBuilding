package grpc.occupantService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OccupantServer {
	
	// server variables
	private final int PORT = 50051;
	private static final Logger LOGGER = Logger.getLogger(OccupantServer.class.getName());
	private Server server;
	
	// This arraylist will contain the database for the trainers
	static ArrayList<TrainerDb> trainers = new ArrayList<>();
	
	
	
	// MAIN METHOD
	public static void main(String[] args) throws IOException, InterruptedException {

		// Instantiate the elevator server class
		final OccupantServer occupantServer = new OccupantServer();

		occupantServer.start();
		occupantServer.blockUntilShutdown();
	}
	

		// START serving requests
		public void start() throws IOException {
			// initialise server
			server = ServerBuilder.forPort(PORT).addService(new OccupantImpl()).build().start();
			// show what number port the server has started listening on
			LOGGER.info("***SERVER STARTED LISTENING ON PORT: " + PORT + "***");
			// If the server shuts down give some information on why
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.err.println("***SHUTTING DOWN SERVER BECAUSE JVM IS SHUTTING DOWN***");
					try {
						OccupantServer.this.stop();
					} catch (InterruptedException e) {
						e.printStackTrace(System.err);
					}
					System.err.println("***SERVER SHUT DOWN***");
				}
			});
			// Create Trainer instances
			trainers.add(0, new TrainerDb(1, "Jeddy Roycraft", "legs", true));	
			trainers.add(0, new TrainerDb(2, "Christan Galpen", "legs", true));
			trainers.add(0, new TrainerDb(3, "Mirella Tuite", "arms", true));
			trainers.add(0, new TrainerDb(4, "Brander Abels", "arms", true));
			trainers.add(0, new TrainerDb(5, "Geoffry Howford", "chest", true));
			trainers.add(0, new TrainerDb(6, "Libby Postance", "chest", true));
			trainers.add(0, new TrainerDb(7, "Hedi Templar", "shoulders", true));
			trainers.add(0, new TrainerDb(8, "Merrel McAtamney", "shoulders", true));
			trainers.add(0, new TrainerDb(9, "Hubert Ledgard", "back", true));
			trainers.add(0, new TrainerDb(10, "Rozelle Coley", "back", true));
			trainers.add(0, new TrainerDb(11, "Asa Wornham", "core", true));
			trainers.add(0, new TrainerDb(12, "Rafe Ivison", "core", true));
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
		
		static class OccupantImpl extends occupantServiceGrpc.occupantServiceImplBase {

			@Override
			public void viewGymTrainers(Empty request, StreamObserver<GymTrainer> responseObserver) {
				System.out.println("Receiving request for gym trainer details");
				
				//Traverse the TrainerDb
				for(int i=0;i<trainers.size();i++) {
					GymTrainer response = GymTrainer.newBuilder().setId(trainers.get(i).getId()).setName(trainers.get(i).getTrainerName())
											.setSpeciality(trainers.get(i).getTrainerSpeciality()).setAvailableNow(trainers.get(i).isAvailableNow()).build();
					responseObserver.onNext(response);
					try {
						//wait for a second
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				responseObserver.onCompleted();
			}

			
		}
	
}
