package ro.sci.booking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;

import org.junit.Test;

public class DataLoader {
	private String ROOMS_FILE_PATH="/Users/emiliaveresezan/Desktop/Java_Resources/rooms.txt";
	private String BOOKING_FILE_PATH="/Users/emiliaveresezan/Desktop/Java_Resources/booking.txt";

	@Test
	public void readRooms() {
		
		Path file = Paths.get(ROOMS_FILE_PATH); //initialize file of type Path(replaces File, which is legacy) by using the static get method in Paths  
		List<Accommodation> rooms = new ArrayList<>(); //initialize list of rooms
		
		readFile(file, rooms);
		
		Path fileOut = Paths.get(BOOKING_FILE_PATH);
		
		writeFile(fileOut);
		
	}
	
	public void readFile(Path file, List<Accommodation> rooms){
		Charset charset = Charset.forName("UTF8");
		try(BufferedReader reader = Files.newBufferedReader(file, charset)){
			String line = null;
			while((line=reader.readLine()) !=null){
				System.out.println(line);
				rooms.add(createAccommodation(line));
			}
		} catch (InvalidAttributeIdentifierException e){
			System.err.println(e.getMessage());
		} catch (IOException x){
				System.err.println("IOException: "+x);
		}
	}
	
	private Accommodation createAccommodation(String line) throws InvalidAttributeIdentifierException {
		Accommodation room = new Accommodation();
		String [] values = line.split(",");
		
		if (values.length !=6) {
			throw new InvalidAttributeIdentifierException();
		}
		room.setRoomId(values[0]);
		room.setType(getAccomodationType(values[1]));
		return room;
	}

	private AccommodationType getAccomodationType(String string) {
		return AccommodationType.SINGLE;
	}

	public void writeFile(Path file){
		Charset charset = Charset.forName("UTF8");
		
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				FileWriter fileWriter = new FileWriter(new File(BOOKING_FILE_PATH))){
				
			writer.write("am un booking\n");
	//		fileWriter.write("am un booking\n");
			
		}catch (IOException e) {
			System.err.println("IOException: "+e);
		}
	}
		
}


