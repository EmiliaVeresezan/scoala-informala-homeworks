/**
 * 
 */
package ro.sci.booking;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.xml.bind.ValidationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Test class for cancel booking functionality.
 * @author Emilia
 *
 */
public class IOCancelBookingTest extends AbstractBookingTest{
	

	private String ROOMS_FILE_PATH = IO_FOLDER + "rooms-cancel-booking.txt";
	private String BOOKING_PERIODS_FILE_PATH = IO_FOLDER + "booking-periods-cancel-booking.txt";
	private String BOOKING_FILE_PATH = IO_FOLDER + "BOOKINGS-cancel-booking.txt";
	

	/**
	 * Initializes the components used to test the functionality: a list of rooms, a list of time periods and a list of bookings.
	 * The rooms are represented as Accommodation objects, periods are represented as Period objects, and bookings are represented as Booking objects.
	 * A Booking object is composed of an Accommodation object and a Period object. Therefore, a room can be associated with multiple booking periods by creating
	 * multiple Booking objects. 
	 * A booking is cancelled by using the method cancelBooking(Booking booking);
	 */	
	@Before
	public void init() {
		rooms = new ArrayList<Accommodation>();
	
		Path file = Paths.get(ROOMS_FILE_PATH);
		readFileForRooms(file, rooms);
		
		periods = new ArrayList<BookingPeriod>();
		file = Paths.get(BOOKING_PERIODS_FILE_PATH);
		readFileFileForBookingPeriods(file, periods);
				
		bookings = new ArrayList<Booking>();
		
		bookings.add(new Booking(rooms.get(0), periods.get(0)));
		bookings.add(new Booking(rooms.get(1), periods.get(1)));
		
		Booking booking = bookings.get(0);
		cancelBooking(booking);
		
	}
	
	/**
	 * Test method for writeFile(Path file).
	 */
	@Test
	public void writerTest(){
		Path fileOut = Paths.get(BOOKING_FILE_PATH);
		writeFile(fileOut);
	}
	
	/**
	 * Creates a file in the location passed as parameter and writes the booking information,
	 * one booking per line.
	 * @param file an absolute file location
	 */
	public void writeFile(Path file){
		Charset charset = Charset.forName("UTF8");
		
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset, StandardOpenOption.CREATE);){
				
			for (Booking b : bookings){
				String accType = b.getRoom().getType().toString();
				String sGuests = Integer.toString(b.getRoom().getMaxGuests());
				Double price = b.getRoom().getFair().getValue();
				String sPrice = Double.toString(price);
				String fromDate=b.getBookingPeriod().getFrom().toString();
				String toDate=b.getBookingPeriod().getTo().toString();
				String sSeason=b.getRoom().getFair().getSeason().getType().toString();
				String sBooking = b.getRoom().getRoomId()+", "+accType+", "+sGuests+", "+sPrice+", "+sSeason+", "+fromDate+", "+toDate+"\n";
				writer.write(sBooking);
			}
					
		}catch (IOException e) {
			System.err.println("IOException: "+e);
		}
	}
	
	
	/**
	 * Takes a Path file object and a list of type BookingPeriod  as parameters and populates 
	 * the list using the information parsed from the file.
	 * @param file	the input file
	 * @param periods  the list of BookingPeriod objects
	 */
	private void readFileFileForBookingPeriods(Path file, List<BookingPeriod> periods) {
		Charset charset = Charset.forName("UTF8");
		try(BufferedReader reader = Files.newBufferedReader(file, charset)){
			String line = null;
			while((line=reader.readLine()) !=null){
				System.out.println(line);
				periods.add(createBookingPeriod(line));
			}
		} catch (InvalidAttributeIdentifierException e){
			System.err.println(e.getMessage());
		} catch (IOException x){
				System.err.println("IOException: "+x);
		}
	}
	
	/**
	 * Takes a line of text passed as parameter and constructs a booking period.
	 * @param line  a line of text that corresponds to one booking period
	 * @return  a BookingPeriod object
	 * @throws InvalidAttributeIdentifierException -if the number of unites of text taken from one line of text is not 3
	 */
	private BookingPeriod createBookingPeriod(String line) throws InvalidAttributeIdentifierException {
		
		BookingPeriod bookingPeriod = new BookingPeriod();
		
		String [] values = line.split(",");
		if (values.length !=3) {
			throw new InvalidAttributeIdentifierException();
		}
		Date fromDate;
		try{
			fromDate = parseDate(values[1]);
			bookingPeriod.setFrom(fromDate);
		} catch (ValidationException ex){
			System.err.println(ex.getMessage());	
		}
		Date toDate;
		try{
			toDate = parseDate(values[2]);
			bookingPeriod.setTo(toDate);
		} catch (ValidationException e){
			System.err.println(e.getMessage());
		}
		return bookingPeriod;	
	}


	/**
	 * Takes a Path object and a list of type Accommodation as parameters and populates 
	 * the list with Accommodation objects constructed using the information parsed 
	 * from the file. 
	 * @param file  the Path object that contains the information, one Accommodation per line.
	 * @param rooms  the list of Accommodation objects
	 */
	public void readFileForRooms(Path file, List<Accommodation> rooms){
		Charset charset = Charset.forName("UTF8");
		try(BufferedReader reader = Files.newBufferedReader(file, charset)){
			String line = null;
			while((line=reader.readLine()) !=null){
				System.out.println(line);
				rooms.add(createRoom(line));
			}
		} catch (InvalidAttributeIdentifierException e){
			System.err.println(e.getMessage());
		} catch (IOException x){
				System.err.println("IOException: "+x);
		}
	}
	
	/**
	 * Creates an Accommodation object using the information parsed from the line of text
	 * passed as parameter.
	 * @param line  a String that contains the info necessary to construct an Accommodation. 
	 * 				the units of information are separated by a comma
	 * @return	an Accommodation object	
	 * @throws InvalidAttributeIdentifierException - if the number of unites of text parsed from the line is not 6
	 */
	private Accommodation createRoom(String line) throws InvalidAttributeIdentifierException{
		Accommodation room = new Accommodation();
		String [] values = line.split(",");
		
		if (values.length !=8) {
			throw new InvalidAttributeIdentifierException();
		}
		room.setRoomId(values[0]);
		room.setType(parseAccomodationType(values[1]));
		
		RoomFair roomFair = new RoomFair();
		try {
			Double price = parseRoomPrice(values[2]);
			roomFair.setValue(price);
		} catch(ValidationException e){
			System.out.println("Invalid price argument: "+ e.getMessage());
		}
		
		Season season = new Season();
		
		Date fromDate;
		try{
			fromDate = parseDate(values[4]);
			season.setFrom(fromDate);
		} catch (ValidationException ex){
			System.err.println(ex.getMessage());	
		}
		Date toDate;
		try{
			toDate = parseDate(values[5]);
			season.setTo(toDate);
		} catch (ValidationException e){
			System.err.println(e.getMessage());
		}
		season.setType(parseSeasonType(values[3]));

		roomFair.setSeason(season);
		
		room.setFair(roomFair);
		
		room.setBedType(parseBedType(values[6]));
		
		try{
		room.setMaxGuests(parseMaxGuest(values[7].trim()));
		} catch (ValidationException e){
			System.out.println(e.getMessage());
		}
		return room;
		
		
	}
	
	
	/**
	 * Takes a String as parameter and parses it to a Double value
	 * @param value the String which needs to be parsed 
	 * @return the value passed as parameter as a Double
	 * @throws ValidationException - if the value cannot be parsed to a Double 
	 * 			because it is not a number, or because it smaller or equal to 0 
	 */
	private Double parseRoomPrice(String value) throws ValidationException {
		Double roomPrice; 
		try {
			roomPrice=Double.parseDouble(value);
		} catch (NumberFormatException e){
			e.printStackTrace();
			throw new ValidationException("The price of the room should be a number.");
		}
		if (roomPrice<=0) {
			throw new ValidationException("The price should be larger than 0");
		}
		return roomPrice;
	}

	/**
	 * Takes a a String as parameter and parses it to a Date object with the format "yyyy-MM-dd".
	 * @param dateToParse  the String to be parsed
	 * @return the Date object
	 * @throws ValidationException - if dateToParse cannot be parsed because it is invalid date, 
	 * or is not in the correct format.
	 */
	private Date parseDate(String dateToParse) throws ValidationException {
		Date date;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateToParse);	
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ValidationException("the date should be in the format yyyy-mm-dd and should be a valid date");
		}
		return date;
	}

	
	private AccommodationType parseAccomodationType(String accType) {
		return AccommodationType.valueOf(accType.trim());
	}
	
	private SeasonType parseSeasonType(String seasonType){
		return SeasonType.valueOf(seasonType.trim());
	}
	
	private BedType parseBedType(String bedType) {
		return BedType.valueOf(bedType.trim());
	}
	
	private int parseMaxGuest(String maxGuests) throws ValidationException {
		int guests;
		try{
			guests=Integer.parseInt(maxGuests);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			throw new ValidationException("maxGuest needs to be a number");
		}
		return guests;
	}


	
	/**
	 * Tests whether the size of the lists bookings and periods has been decreased. 
	 */
	@Test
	public void componentsSizeTest(){
		assertEquals(1, bookings.size());
		assertEquals(1, periods.size());
	}
	
	/**
	 * test that the booking was cancelled by checking that the following booking 
	 * is now at the index of the cancelled booking
	 */
	@Test
	public void cancelBookingTest() {
		
		assertEquals(AccommodationType.ROYAL, bookings.get(0).getRoom().getType());
		assertEquals(BedType.QUEEN_SIZE, bookings.get(0).getRoom().getBedType());
		assertEquals(80, bookings.get(0).getRoom().getFair().getValue(),0);
		assertEquals(getDate(2016, 8, 9), bookings.get(0).getBookingPeriod().getFrom());
		assertEquals(getDate(2016, 8, 12), bookings.get(0).getBookingPeriod().getTo());
	}
	
	
	
	/**
	 * Removes booking from booking list and period from BookingPeriods list.
	 * @param booking
	 */
	public void cancelBooking(Booking booking) {
		for (Booking b : bookings){
			if (b.equals(booking)){
				BookingPeriod per = b.getBookingPeriod();
				bookings.remove(b);
				periods.remove(per);
				return;
			}
		}
	}
	
	@After
	public void destroy (){
		rooms = null;
		periods = null;
		bookings = null;
	}
	
	/**
	 * Creates a new instance of Accommodation.
	 * @param type     the AccommodationType to be created
	 * @param fair     the price of the Accommodation
	 * @param bedType  the type of bed the accommodation contains 
	 * @param guests   the maximum number of guests for the accommodation
	 * @return		   an Accommodation object	
	 */
	private Accommodation createNewRoom(AccommodationType type, RoomFair fair, BedType bedType, int guests) {
		Accommodation accomodation = new Accommodation();
		accomodation.setType(type);
		accomodation.setFair(fair);
		accomodation.setBedType(bedType);
		return accomodation;
	}
	
	/**
	 * Creates a new instance of RoomFair.
	 * @param value    the value of the room
	 * @param season   the season for which the value is the price
	 * @return		   a RoomFair object
	 */
	private RoomFair createRoomFair(double value, Season season) {
		RoomFair roomFair = new RoomFair();
		roomFair.setSeason(season);
		roomFair.setValue(value);
		return roomFair;
	}
	
	/**
	 * Creates a new instance of Season.
	 * @param type  the SeasonType of the Season
	 * @param from	the date the season begins
	 * @param to	the date the season ends
	 * @return		a Season object
	 */
	private Season createSeason(SeasonType type, Date from, Date to) {
		Season season = new Season();
		season.setFrom(from);
		season.setTo(to);
		season.setType(type);
		return season;
	}
	
	/**
	 * Creates a new Date instance.
	 * @param year   the year 
	 * @param month  the month as a number between 0 and 11, where 0 represents January 
	 * @param day	 day of the month
	 * @return		 a Date object
	 */
	private Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

		return c.getTime();
	}


}
