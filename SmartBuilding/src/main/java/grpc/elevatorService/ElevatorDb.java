package grpc.elevatorService;

public class ElevatorDb {
	
	//variables
	private int id;
	private int currentFloor;
	private int destinationFloor;
	private final int LOWESTFLOOR = 0;
	private final int HIGHESTFLOOR = 10;
	private final int CAPACITYLIMIT = 8;
	private int currentCapacity;
	boolean isMoving;
	enum travelDirection{
		UP, DOWN
	}
	
	
	//Constructor
	public ElevatorDb(int id, int currentFloor, int destinationFloor,int currentCapacity, boolean isMoving) {
		this.id = id;
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.currentCapacity = currentCapacity;
		this.isMoving = isMoving;
		
	}
	
	//GETTERS AND SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	public int getCurrentFLoor() {
		return this.currentFloor;
	}
	
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	
	public int getDestinationFloor() {
		return this.destinationFloor;
	}
	
	public int getLowestFloor() {
		return this.LOWESTFLOOR;
	}
	
	public int getHighestFloor() {
		return this.HIGHESTFLOOR;
	}
	
	public int getCapacityLimit() {
		return this.CAPACITYLIMIT;
	}
	
	public void setCurrentCapactity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}
	
	public int getCurrentCapacity() {
		return this.currentCapacity;
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean getIsMoving() {
		return this.isMoving;
	}
	

}
