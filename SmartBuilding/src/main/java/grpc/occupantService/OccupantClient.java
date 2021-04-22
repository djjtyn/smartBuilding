package grpc.occupantService;


import java.util.Iterator;
import java.util.logging.Logger;

import grpc.lightingService.LightingClient;
import grpc.occupantService.occupantServiceGrpc.occupantServiceBlockingStub;
import grpc.occupantService.occupantServiceGrpc.occupantServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


public class OccupantClient {
	private static occupantServiceBlockingStub blockingStub;
	private static occupantServiceStub asyncStub;
	final private static Logger logger = Logger.getLogger(LightingClient.class.getName());
	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();
		//initialise the stubs 
		blockingStub = occupantServiceGrpc.newBlockingStub(channel);
		asyncStub = occupantServiceGrpc.newStub(channel);
		
		viewGymTrainers();
	}
	
	public static void viewGymTrainers() {
		//Request
		Empty request = Empty.newBuilder().build();
		//Response
		try {
			Iterator<GymTrainer> responseIterator = blockingStub.viewGymTrainers(request);
			int iterateCount = 1;
			while(responseIterator.hasNext()) {
				System.out.println("***Gym Trainer " + iterateCount + "***");
				GymTrainer response = responseIterator.next();
				System.out.println(response);
				iterateCount++;
			}
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
		logger.info("All gym trainer details sent!");		
	}
}
