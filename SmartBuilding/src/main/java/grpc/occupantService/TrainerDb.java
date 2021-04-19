//This class is used as a gym trainer database

package grpc.occupantService;

public class TrainerDb {
	
	private int id;
	private String trainerName;
	private String trainerSpeciality;
	boolean availableNow;
	Time nextAvailableTime;
	
	public TrainerDb() {}
	public TrainerDb(int id, String name, String speciality, boolean available) {
		this.id = id;
		this.trainerName = name;
		this.trainerSpeciality = speciality;
		this.availableNow = available;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTrainerName() {
		return trainerName;
	}


	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}


	public String getTrainerSpeciality() {
		return trainerSpeciality;
	}


	public void setTrainerSpeciality(String trainerSpeciality) {
		this.trainerSpeciality = trainerSpeciality;
	}


	public boolean isAvailableNow() {
		return availableNow;
	}


	public void setAvailableNow(boolean availableNow) {
		this.availableNow = availableNow;
	}


	public Time getNextAvailableTime() {
		return nextAvailableTime;
	}


	public void setNextAvailableTime(Time nextAvailableTime) {
		this.nextAvailableTime = nextAvailableTime;
	}


	class Time{
		int hour;
		int minute;
		final int MAXHOUR = 24;
		final int MAXMINUTE = 60;
		
		//throw an exception if its an incorrect hour using setters and constructors
		public Time() {}
		public Time(int hour, int minute) throws Exception {
			if(hour>MAXHOUR || minute>MAXMINUTE) {
				throw new Exception("You have entered an invalid time");
			} else {
				this.hour = hour;
				this.minute = minute;
			}
		}
		
		public void setHour(int hour) throws Exception {
			if(hour> MAXHOUR) {
				throw new Exception("The maximum hour number that can be accpeted is 24");
			}else {
				this.hour = hour;
			}
		}
		
		//throw an exception if its an incorrect minute
		public void setMinute(int minute) throws Exception{
			if(minute>MAXMINUTE) {
				throw new Exception("The maximum minute number that can be accepted is 60");
			}else {
				this.minute = minute;
			}
		}
		
		public int getHour() {
			return this.hour;
		}
		
		public int getMinute() {
			return this.minute;
		}
	}
}
