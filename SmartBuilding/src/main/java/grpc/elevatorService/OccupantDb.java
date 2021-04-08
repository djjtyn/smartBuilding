//This class is used to read the data from smartBuildingDb found in the resources folder

package grpc.elevatorService;

import java.util.ArrayList;

public class OccupantDb {
	
	private int id;
	private String name;
	private int roomFloor;
	private int roomNumber;
	ArrayList<Integer> accessToFloor = new ArrayList<>();
	
	//constructor
	public OccupantDb(int id, String name, int roomFloor, int roomNumber, ArrayList<Integer> accessToFloor) {
		this.id = id;
		this.name = name;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.accessToFloor = accessToFloor;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoomFloor() {
		return roomFloor;
	}
	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public ArrayList<Integer> getAccessToFloor() {
		return accessToFloor;
	}
	public void setAccessToFloor(ArrayList<Integer> accessToFloor) {
		this.accessToFloor = accessToFloor;
	}
}
