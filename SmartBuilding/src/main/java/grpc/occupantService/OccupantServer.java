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
			trainers.add(0, new TrainerDb(1, "Jeddy Roycraft", "legs", "351-249-6784","JeddyRoycraft@theGym.ie" ,true));	
			trainers.add(0, new TrainerDb(2, "Christan Galpen", "legs", "527-601-2945","ChristanGalpen@theGym.ie", true));
			trainers.add(0, new TrainerDb(3, "Mirella Tuite", "arms","831-313-2896","MirellaTuite@theGym.ie", true));
			trainers.add(0, new TrainerDb(4, "Brander Abels", "arms", "455-776-8738","BranderAbelst@theGym.ie",true));
			trainers.add(0, new TrainerDb(5, "Geoffry Howford", "chest","707-617-7422","GeoffryHowford@theGym.ie" ,true));
			trainers.add(0, new TrainerDb(6, "Libby Postance", "chest","828-202-0601","LibbyPostance@theGym.ie", true));
			trainers.add(0, new TrainerDb(7, "Hedi Templar", "shoulders","277-543-4931","HediTemplar@theGym.ie", true));
			trainers.add(0, new TrainerDb(8, "Merrel McAtamney", "shoulders","489-655-9332","MerrelMcAtamney@theGym.ie", true));
			trainers.add(0, new TrainerDb(9, "Hubert Ledgard", "back","749-210-8912","HubertLedgard@theGym.ie", true));
			trainers.add(0, new TrainerDb(10, "Rozelle Coley", "back","393-136-9775","RozelleColeyt@theGym.ie", true));
			trainers.add(0, new TrainerDb(11, "Asa Wornham", "core", "913-736-3924","AsaWornham@theGym.ie",true));
			trainers.add(0, new TrainerDb(12, "Rafe Ivison", "core","432-442-9402","RafeIvison@theGym.ie", true));
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
					GymTrainer response = GymTrainer.newBuilder()
											.setId(trainers.get(i).getId()).setName(trainers.get(i).getTrainerName())
											.setSpeciality(trainers.get(i).getTrainerSpeciality()).setPhoneNumber(trainers.get(i).getPhoneNumber())
											.setEmail(trainers.get(i).getEmailAddress()).setAvailableNow(trainers.get(i).isAvailableNow()).build();
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
