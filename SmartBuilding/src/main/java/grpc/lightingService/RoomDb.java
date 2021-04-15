//This class allows room instances to be created and their values set/adjusted

package grpc.lightingService;

public class RoomDb {
	
	private int id;
	private String roomName;
	private int brightness;
	private int blindDrawnAmount;
	private double temperature;
	
	//OVERLOAD CONSTRUCTOR
	public RoomDb(int id, String roomName, int brightness, int blindDrawnAmount, double temperature) {
		this.id = id;
		this.roomName = roomName;
		this.brightness = brightness;
		this.blindDrawnAmount = blindDrawnAmount;
		this.temperature = temperature; 
	}
	
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	public int getBlindDrawnAmount() {
		return blindDrawnAmount;
	}
	public void setBlindDrawnAmount(int blindDrawnAmount) {
		this.blindDrawnAmount = blindDrawnAmount;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

}
