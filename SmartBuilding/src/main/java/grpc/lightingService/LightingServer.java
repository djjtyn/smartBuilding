//This is the server class for the lighting control service

package grpc.lightingService;

import grpc.lightingService.lightingGrpc.lightingImplBase;

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

public class LightingServer extends lightingImplBase {

	// Method to get properties from the occupant.properties file
	private Properties getProperties() {

		Properties prop = null;
		String dir = System.getProperty("user.dir"); // Get the users current directory to be used with file location
														// below
		try (InputStream input = new FileInputStream(dir + "/src/main/resources/lightingService/lighting.properties")) {
			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Lighting Service properies ...");
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

	public static void main(String[] args) throws IOException, InterruptedException {
		// Instantiate the lighting server class
		LightingServer lightingServer = new LightingServer();

		// Get the properties info
		Properties prop = lightingServer.getProperties();

		// Register the service
		lightingServer.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {

			Server server = ServerBuilder.forPort(port).addService(lightingServer).build().start();

			System.out.println("Lighting Server started listening on " + port);

			server.awaitTermination();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void adjustLighting(Room request, StreamObserver<LightingResponse> responseObserver) {
		// If the lights are trying to be adjusted above 100%
		if (request.getIntAdjust() > 100) {
			LightingResponse response = LightingResponse.newBuilder().setBrightnessValue(100)
					.setLightingMessage("Maximum the brighthness can be adjusted to is 100%."
							+ " You have attempted to adjust it to " + request.getIntAdjust()
							+ "%. Resorting to 100% lighting. Room: " + request.getRoomName()
							+ " lighting adjusted from " + request.getBrightness() + "% to 100%")
					.build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
		// If the lights are trying to be adjusted below 0%
		else if (request.getIntAdjust() < 0) {
			LightingResponse response = LightingResponse.newBuilder().setBrightnessValue(0)
					.setLightingMessage("Minimum the brightness can be adjusted to is 0%. "
							+ "You have attempted to adjust it to " + request.getIntAdjust()
							+ "%. Resorting to 0% lighting(Lights powered off). Room: " + request.getRoomName()
							+ " lighting adjusted from " + request.getBrightness() + "% to 0%")
					.build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
		// If the lights are already at the desired setting
		else if (request.getIntAdjust() == request.getBrightness()) {
			LightingResponse response = LightingResponse.newBuilder()
					.setLightingMessage("The lights are already set at your desired setting- " + request.getIntAdjust()
							+ "%. Room: " + request.getRoomName() + " lighting remaining at " + request.getBrightness()
							+ "%")
					.build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} // If its a valid request
		else {
			System.out.println("Receiving request to change the brightness of room: " + request.getRoomName() + " from "
					+ request.getBrightness() + "% to " + request.getIntAdjust() + "%");
			LightingResponse response = LightingResponse.newBuilder().setBrightnessValue(request.getIntAdjust())
					.setLightingMessage("Room: " + request.getRoomName() + " lighting adjusted from "
							+ request.getBrightness() + "% to " + request.getIntAdjust() + "%")
					.build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
	}

	@Override
	public StreamObserver<Room> adjustLightingMultiRoom(StreamObserver<LightingResponse> responseObserver) {

		return new StreamObserver<Room>() {
			// Create array list to store room names and their desired lighting settings
			ArrayList<RoomDb> rooms = new ArrayList<>();
			

			@Override
			public void onNext(Room value) {
				// Add the room name and its desired brightness level to to array list
				rooms.add(new RoomDb(value.getRoomName(), value.getIntAdjust()));
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				// System.out.println("Received requests for all rooms selected");
				// Create a String builder so all the room names and their new brightness value
				// can be seen
				StringBuilder sb = new StringBuilder();
				// Traverse all the rooms in the room array list
				for (int i = 0; i < rooms.size(); i++) {
					sb.append(rooms.get(i).getRoomName() + " brightness adjusted to "
							+ rooms.get(i).getBrightness() + "%.");
				}
				// convert the string builder to a string and use it as the responses message
				String responseString = sb.toString();
				LightingResponse.Builder response = LightingResponse.newBuilder();
				response.setLightingMessage(responseString);
				responseObserver.onNext(response.build());
				responseObserver.onCompleted();
			}

		};
	}
}
