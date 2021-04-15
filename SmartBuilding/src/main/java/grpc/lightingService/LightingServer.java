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
				// TODO Auto-generated method stub
				super.adjustLighting(request, responseObserver);
			}

			@Override
			public StreamObserver<Room> adjustLightingMultiRoom(StreamObserver<LightingResponse> responseObserver) {
				// TODO Auto-generated method stub
				return super.adjustLightingMultiRoom(responseObserver);
			}
			
		}

}
