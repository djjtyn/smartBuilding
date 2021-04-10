//This class is used to read the data from smartBuildingDb found in the resources folder

package grpc.elevatorService;

import java.util.ArrayList;

public class OccupantDb {
	
	private int id;
	private String name;
	private int roomFloor;
	private int roomNumber;
	private int accessToFloor;
	
	//constructor
	public OccupantDb(int id, String name, int roomFloor, int roomNumber, int accessToFloor) {
		this.id = id;
		this.name = name;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.accessToFloor = accessToFloor;
		
	}
	
	public OccupantDb(int parseInt, String name2, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
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
	public int getAccessToFloor() {
		return accessToFloor;
	}
	public void setAccessToFloor(int accessToFloor) {
		this.accessToFloor = accessToFloor;
	}
}
