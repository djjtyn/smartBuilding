package grpc.occupantService;

import grpc.occupantService.occupantServiceGrpc.occupantServiceImplBase;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OccupantServer extends occupantServiceImplBase {

	// This arraylist will contain the database for the trainers
	static ArrayList<TrainerDb> trainers = new ArrayList<>();

	// Method to get properties from the occupant.properties file
	private Properties getProperties() {

		Properties prop = null;
		String dir = System.getProperty("user.dir"); // Get the users current directory to be used with file location
														// below
		try (InputStream input = new FileInputStream(dir + "/src/main/resources/occupantService/occupant.properties")) {
			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Occupant Service properies ...");
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
			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

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

		// Create Trainer instances
		trainers.add(0, new TrainerDb(1, "Jeddy Roycraft", "legs", "351-249-6784", "JeddyRoycraft@theGym.ie", true));
		trainers.add(0, new TrainerDb(2, "Christan Galpen", "legs", "527-601-2945", "ChristanGalpen@theGym.ie", true));
		trainers.add(0, new TrainerDb(3, "Mirella Tuite", "arms", "831-313-2896", "MirellaTuite@theGym.ie", true));
		trainers.add(0, new TrainerDb(4, "Brander Abels", "arms", "455-776-8738", "BranderAbelst@theGym.ie", true));
		trainers.add(0, new TrainerDb(5, "Geoffry Howford", "chest", "707-617-7422", "GeoffryHowford@theGym.ie", true));
		trainers.add(0, new TrainerDb(6, "Libby Postance", "chest", "828-202-0601", "LibbyPostance@theGym.ie", true));
		trainers.add(0, new TrainerDb(7, "Hedi Templar", "shoulders", "277-543-4931", "HediTemplar@theGym.ie", true));
		trainers.add(0,
				new TrainerDb(8, "Merrel McAtamney", "shoulders", "489-655-9332", "MerrelMcAtamney@theGym.ie", true));
		trainers.add(0, new TrainerDb(9, "Hubert Ledgard", "back", "749-210-8912", "HubertLedgard@theGym.ie", true));
		trainers.add(0, new TrainerDb(10, "Rozelle Coley", "back", "393-136-9775", "RozelleColeyt@theGym.ie", true));
		trainers.add(0, new TrainerDb(11, "Asa Wornham", "core", "913-736-3924", "AsaWornham@theGym.ie", true));
		trainers.add(0, new TrainerDb(12, "Rafe Ivison", "core", "432-442-9402", "RafeIvison@theGym.ie", true));

		// Instantiate the occupant server
		OccupantServer occupantServer = new OccupantServer();

		// Get the properties info
		Properties prop = occupantServer.getProperties();

		// Register the service
		occupantServer.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {

			Server server = ServerBuilder.forPort(port).addService(occupantServer).build().start();

			System.out.println("Occupant Server started listening on " + port);

			server.awaitTermination();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void viewGymTrainers(Empty request, StreamObserver<GymTrainer> responseObserver) {
		System.out.println("Receiving request for gym trainer details");

		// Traverse the TrainerDb
		for (int i = 0; i < trainers.size(); i++) {
			GymTrainer response = GymTrainer.newBuilder().setId(trainers.get(i).getId())
					.setName(trainers.get(i).getTrainerName()).setSpeciality(trainers.get(i).getTrainerSpeciality())
					.setPhoneNumber(trainers.get(i).getPhoneNumber()).setEmail(trainers.get(i).getEmailAddress())
					.setAvailableNow(trainers.get(i).isAvailableNow()).build();
			responseObserver.onNext(response);
			try {
				// wait for a second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		responseObserver.onCompleted();
	}

}
