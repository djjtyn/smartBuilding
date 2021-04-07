//This class is used to read the data from smartBuildingDb found in the resources folder

package grpc.elevatorService;

import com.google.protobuf.util.JsonFormat;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class OccupantDb {

	// Gets the smartBuilding Database data
	public static URL getDatabase() {
		return ElevatorServer.class.getResource("smartBuildingDb.json");
	}

	// Read occupant data from the smartBuildingDb file
	public static List<Occupant> parseDatabase(URL file) throws IOException {
		InputStream input = file.openStream();
		try {
			Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"));
			try {
				OccupantDatabase.Builder occupantDb = OccupantDatabase.newBuilder();
				JsonFormat.parser().merge(reader, occupantDb);
				return occupantDb.getOccupantList();
			} finally {
				reader.close();
			}
		} finally {
			input.close();
		}
	}
	
	//Gets the floor number for a particular user
	public static int getFloorNumber(Occupant person) {
		return person.getRoomFloor();
	}

}
