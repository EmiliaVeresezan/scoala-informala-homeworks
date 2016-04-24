package ro.sci.booking;

import static org.junit.Assert.*;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * BookTest is the test class for the functionality of booking a room for a specified period.
 *  
 * @author Emilia
 *
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.xml.bind.ValidationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.sci.booking.exception.BookingPeriodValidationException;

/**
 * 
 * Test class for cancel booking functionality.
 * @author Emilia
 *
 */
public class IOBookingTest {

	private String ROOMS_FILE_PATH="/Users/emiliaveresezan/Desktop/Workspace_eclipse/booking/files/rooms-booking-test.txt";
	private String BOOKING_PERIODS_FILE_PATH="/Users/emiliaveresezan/Desktop/Workspace_eclipse/booking/files/booking-periods.txt";
	private String BOOKING_FILE_PATH="/Users/emiliaveresezan/Desktop/Workspace_eclipse/booking/files/BOOKINGS-booking-test.txt";

	
	List<Accommodation> rooms ;

	List<BookingPeriod> periods;

	List<Booking> bookings;

	/**
	 * Initializes the components used to test the booking functionality: a list of rooms, a list of time periods and a list of bookings.
	 * The rooms are represented as Accommodation objects, periods are represented as Period objects, and bookings are represented as Booking objects.
	 * A Booking object is composed of an Accommodation object and a Period object. Therefore, a room can be associated with multiple booking periods by creating
	 * multiple Booking objects. 
	 */
	@Before
	public void init() {
		
		rooms = new ArrayList<Accommodation>();
		Path fileIn = Paths.get(ROOMS_FILE_PATH);
		readFileForRooms(fileIn, rooms);
		System.out.println("Number of rooms: "+rooms.size());

		
		periods = new ArrayList<BookingPeriod>();
		fileIn = Paths.get(BOOKING_PERIODS_FILE_PATH);
		readFileFileForBookingPeriods(fileIn, periods);
		System.out.println("Number of periods: "+periods.size());

		
		bookings = new ArrayList<Booking>();
		
		bookings.add(new Booking(rooms.get(0), periods.get(0)));
		bookings.add(new Booking(rooms.get(0), periods.get(1)));
		bookings.add(new Booking(rooms.get(0), periods.get(2)));
		bookings.add(new Booking(rooms.get(0), periods.get(3)));
		System.out.println("Number of bookings: "+bookings.size());
		
		
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
				Double price = b.getRoom().getFair().getValue();
				String sPrice = Double.toString(price);
				String fromDate=b.getBookingPeriod().getFrom().toString();
				String toDate=b.getBookingPeriod().getTo().toString();
				String sSeason=b.getRoom().getFair().getSeason().getType().toString();
				String sBooking = b.getRoom().getRoomId()+", "+accType+", "+sPrice+", "+sSeason+", "+fromDate+", "+toDate+"\n";
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
		
		if (values.length !=6) {
			throw new InvalidAttributeIdentifierException();
		}
		room.setRoomId(values[0]);
		System.out.println(values[1]);
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

	/**
	 * Tests all the possible cases in which the given period is not available for booking.
	 * The tested method returns true when the given period has already been booked partially or completely.
	 */
	@Test
	public void findAccomodationNotAvailableTest(){
		
		//period is not available for booking for room1- both from and to dates are included in a booked period
		Date from = getDate(2016,8, 22);
		Date to = getDate(2016, 8, 23);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
	
		//period is not available for booking for room1 - from date is included in a booked period, to date is not
		from = getDate(2016,8, 22);
		to = getDate(2016, 8, 25);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
		//period is not available for booking for room1 - to date is included in a booked period, from date is not
		from = getDate(2016,8, 20);
		to = getDate(2016, 8, 23);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
		//period is not available for booking for room1 - from date is equal to first day of booked period
		from = getDate(2016,8, 21);
		to = getDate(2016, 8, 24);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));					
			
		//period is not available for booking for room1 - from date is before the first day of the booked period and 
		// the to date is after the last day of the booked period
		from = getDate(2016,8, 20);
		to = getDate(2016, 8, 25);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
	}
	
	/**
	 * Tests all the possible cases in which the given period is available for booking.
	 * The tested method returns false when the given period is not already booked partially or completely.
	 */
	@Test
	public void findAccomodationAvailableTest(){
		
		//period is available for booking for room1 - from and to dates are after the booked period
				Date from = getDate(2016,8, 25);
				Date to = getDate(2016, 8, 26);
				assertFalse(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
				
				//period is available for booking for room1 - from and to dates are before the booked period
				from = getDate(2016,8, 16);
				to = getDate(2016, 8, 20);
				assertFalse(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));		
				
				//period is available for booking for room1 - from date is equal to last day of booked period
				//and to date is after the last day of the booked period
				from = getDate(2016, 8, 24);
				to = getDate(2016, 8, 26);
				assertFalse(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));		
	}
	
	/**
	 * Searches for the given AccommodationType, for the given period in the list of bookings.
	 * Returns <code>true</code> if the the AccomodationType for the given period is already booked partially or completely.
	 * Returns <code>false</code> if the accommodation type for the given period has not been booked partially or completely. 
	 * @param type  the AccommodationType to be searched for
	 * @param from  the starting date for the period of the AccommodationType that is searched for
	 * @param to    the end date for the period of the AccomodationType that is searched for
	 * @return      <code>true</code> if the accommodation for the <code> from </code> and <code>to</code> period
	 * 				was found among the existing bookings.
	 * 				<code>false</code> otherwise 
	 */
	private boolean findAccomodationTypeByPeriod(AccommodationType type, Date from, Date to) {
		boolean found = false;
		for (Booking booking : bookings) {
			if ((booking.getBookingPeriod().getFrom().after(from)) && (booking.getBookingPeriod().getTo().before(to))) {
				found = true;
			}
			else {
			if (booking.getRoom().getType().equals(type)){
				if (((booking.getBookingPeriod().getFrom().before(from) || booking.getBookingPeriod().getFrom().equals(from))
						&& (booking.getBookingPeriod().getTo().after(from)))
						|| ((booking.getBookingPeriod().getFrom().before(to) && booking.getBookingPeriod().getTo().after(to)))){
					found = true;
				}
			}
					
			}
			if (found) {
				break;
			}
		}
		return found;
	}
	
	
	/**
	 * Tests whether booking is made for unexpected input dates.
	 */
	@Test
	public void unexpectedInputTest() {
		
		//unexpected input
		Date from = getDate(1100, 67, 190);
		Date to = getDate(1, 9, 19);
		
		addAccommodationTypeByPeriod(AccommodationType.SUITE, from, to);
		
		//booking is not made for illogical input  
		assertEquals(5, bookings.size());
		assertEquals(5, periods.size());
		assertEquals(AccommodationType.SUITE, bookings.get(4).getRoom().getType());
		assertEquals(from, bookings.get(4).getBookingPeriod().getFrom());
		assertEquals(to, bookings.get(4).getBookingPeriod().getTo());
		assertEquals(90, bookings.get(4).getRoom().getFair().getValue(), 0);

	}
	
	/**
	 * Tests that a booking is added if the given AccommodationType is available for the given period.
	 */
	@Test
	public void addAccomodationTypeAvailableTest() {
		
		//period is available for booking
		Date from = getDate(2016, 10, 22);
		Date to = getDate(2016, 10, 25);
		
		addAccommodationTypeByPeriod(AccommodationType.SUITE, from, to);
		
		//tests whether the booking has been made  
		assertEquals(5, bookings.size());
		assertEquals(5, periods.size());
		assertEquals(AccommodationType.SUITE, bookings.get(4).getRoom().getType());
		assertEquals(from, bookings.get(4).getBookingPeriod().getFrom());
		assertEquals(to, bookings.get(4).getBookingPeriod().getTo());
		assertEquals(90, bookings.get(4).getRoom().getFair().getValue(), 0);
	}
	
	/**
	 * Tests that a booking is not added if the given AccommodationType is not available for the given period.
	 */
	@Test
	public void addAccommodationTypeNotAvailableTest() {
		//period is not available for booking
		Date from = getDate(2016, 9, 22);
		Date to = getDate(2016, 9, 23);
		
		addAccommodationTypeByPeriod(AccommodationType.SUITE, from, to);
		
		//tests that the booking has not been made 
		//and therefore not added to the bookings list and  periods list 
		assertEquals(5, periods.size());
		assertEquals(5, bookings.size());
			}
	
	/**
	 * If no booking already exists for the given AccommodationType for the given period, a booking is made.  
	 * @param type  the AccommodationType to be booked
	 * @param from  the starting date for the period of the AccommodationType to be booked
	 * @param to    the end date for the period of the AccomodationType to be booked
	 */
	private void addAccommodationTypeByPeriod(AccommodationType type, Date from, Date to){
		//if the accommodation by period could not be found in the existing bookings
		if (!findAccomodationTypeByPeriod(type, from, to)){
			for (Accommodation room : rooms ){
				//search the rooms until you find a room that has the required AccomodationType
				if (room.getType().equals(type)){
					//when the room is found create Booking and add it to the bookings list  
					BookingPeriod bookingPeriod = new BookingPeriod(from, to);
					periods.add(bookingPeriod);
					Booking booking = new Booking(room, bookingPeriod);
					bookings.add(booking);
					return;
				}
			}
		}
		//else do nothing
	}
	
	/**
	 * Tests that the an Accommodation cannot be booked twice for the same period.
	 *  
	 */
	@Test
	public void addAccommodationTwiceTest() {
		
		//this period is available for booking
		Date from = getDate(2016, 6, 10);
		Date to = getDate(2016, 6, 12);
		
		//Create booking and add it to the bookings and periods list
		addAccommodationTypeByPeriod(AccommodationType.SUITE, from, to);
		
		assertEquals(5, bookings.size());
		assertEquals(5, periods.size());
		assertEquals(AccommodationType.SUITE, bookings.get(4).getRoom().getType());
		assertEquals(from, bookings.get(4).getBookingPeriod().getFrom());
		assertEquals(to, bookings.get(4).getBookingPeriod().getTo());
		
		//make the same booking again
		addAccommodationTypeByPeriod(AccommodationType.SUITE, from, to);
		//test that booking has not been added
		assertEquals(5, bookings.size());
		assertEquals(5, periods.size());
	}
	
	/**
	 * Sets to null the object lists used for testing.
	 */
	@After
	public void destroy(){
		rooms = null;
		periods = null;
		bookings = null;
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
