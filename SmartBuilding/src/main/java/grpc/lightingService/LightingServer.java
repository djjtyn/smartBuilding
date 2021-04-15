//This is the server class for the lighting control service

package grpc.lightingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class LightingServer {
	
	// server variables
	private final int PORT = 50051;
	private static final Logger LOGGER = Logger.getLogger(LightingServer.class.getName());
	private Server server;

	public static void main(String[] args) throws IOException, InterruptedException {
		// Instantiate the lighting server class
		final LightingServer lightingServer = new LightingServer();
		lightingServer.start();
		lightingServer.blockUntilShutdown();
	}
	
	// START serving requests
		public void start() throws IOException {
			// initialise server
			server = ServerBuilder.forPort(PORT).addService(new LightingImpl()).build().start();
			// show what number port the server has started listening on
			LOGGER.info("***SERVER STARTED LISTENING ON PORT: " + PORT + "***");
			// If the server shuts down give some information on why
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.err.println("***SHUTTING DOWN SERVER BECAUSE JVM IS SHUTTING DOWN***");
					try {
						LightingServer.this.stop();
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
		
		static class LightingImpl extends lightingGrpc.lightingImplBase{

			@Override
			public void adjustLighting(Room request, StreamObserver<LightingResponse> responseObserver) {
				//If the lights are trying to be adjusted above 100%
				if(request.getIntAdjust() >= 100) {
					System.out.println("Maximum the brighthness can be adjusted to is 100%. You have attempted to adjust it to " + request.getIntAdjust() + "%");
					System.out.println("Resorting to 100% lighting");
					LightingResponse response = LightingResponse.newBuilder().setBrightnessValue(100).setLightingMessage("Maximum the brighthness can be adjusted to is 100%."
							+ " You have attempted to adjust it to " + request.getIntAdjust() +"%. Resorting to 100% lighting. Room: " + request.getRoomName() 
					+ " lighting adjusted from " + request.getBrightness() + "% to 100%").build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();
				}
				//If the lights are trying to be adjusted below 0%
				else if(request.getIntAdjust()<0) {
					System.out.println("Minimum the brightness can be adjusted to is 0%. You have attempted to adjust it to " + request.getIntAdjust() + "%");
					System.out.println("Resorting to 0% (Lights powered off");
					LightingResponse response = LightingResponse.newBuilder().setBrightnessValue(0).setLightingMessage("Minimum the brightness can be adjusted to is 0%. "
							+ "You have attempted to adjust it to " + request.getIntAdjust() + "%. Resorting to 0% lighting(Lights powered off). Room: " + request.getRoomName() 
					+ " lighting adjusted from " + request.getBrightness() + "% to 0%").build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();
				}
				//If the lights are already at the desired setting
				else if(request.getIntAdjust() == request.getBrightness()) {
					System.out.println("The lights are already set at your desired setting: " + request.getIntAdjust() + "%");
					LightingResponse response = LightingResponse.newBuilder().setLightingMessage("The lights are already set at your desired setting- " + request.getIntAdjust() + "%. Room: " + 
					request.getRoomName() + " lighting remaining at " + request.getBrightness() + "%").build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();		
				}//If its a valid request
				else {
					System.out.println("Receiving request to change the brightness of room: " + request.getRoomName() + " from " + 
							request.getBrightness() + "% to %" +  request.getIntAdjust());		
					LightingResponse response = LightingResponse.newBuilder().setBrightnessValue(request.getIntAdjust()).setLightingMessage("Room: " + request.getRoomName() 
					+ " lighting adjusted from " + request.getBrightness() + "% to " + request.getIntAdjust() + "%").build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();	
				}	
			}

			@Override
			public StreamObserver<Room> adjustLightingMultiRoom(StreamObserver<LightingResponse> responseObserver) {
				// TODO Auto-generated method stub
				return super.adjustLightingMultiRoom(responseObserver);
			}
			
		}

}
