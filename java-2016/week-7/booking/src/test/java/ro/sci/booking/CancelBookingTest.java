/**
 * 
 */
package ro.sci.booking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Test class for cancel booking functionality.
 * @author Emilia
 *
 */
public class CancelBookingTest {
	
	List<Accommodation> rooms;

	List<BookingPeriod> periods;

	List<Booking> bookings;
	
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
		
		Date from = getDate(2016, 8, 28);
		Date to = getDate(2016, 10, 31);
		Season season = createSeason(SeasonType.LOW, from, to);
		
		RoomFair roomFair1 = createRoomFair(90, season);
		Accommodation room1 = createNewRoom(AccommodationType.SUITE, roomFair1, BedType.SINGLE, 5);
		rooms.add(room1);
		
		RoomFair roomFair2 = createRoomFair(80, season);
		Accommodation room2 = createNewRoom(AccommodationType.ROYAL, roomFair2, BedType.QUEEN_SIZE, 2);
		rooms.add(room2);
		
		periods = new ArrayList<BookingPeriod>();
		
		periods.add(new BookingPeriod(getDate(2016,9,1), getDate(2016,9,5)));
		periods.add(new BookingPeriod(getDate(2016,9,9), getDate(2016,9,12)));
				
		bookings = new ArrayList<Booking>();
		
		bookings.add(new Booking(room1, periods.get(0)));
		bookings.add(new Booking(room2, periods.get(1)));
		
		Booking booking = bookings.get(0);
		cancelBooking(booking);
		
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
		assertEquals(getDate(2016, 9, 9), bookings.get(0).getBookingPeriod().getFrom());
		assertEquals(getDate(2016, 9, 12), bookings.get(0).getBookingPeriod().getTo());
	}
	
	/**
	 * Tests behavior of the app when there is an attempt to cancel an non-existant booking.
	 * This is an unexpected input situation.
	 * I'm not sure if this qualifies as a negative flow test or as a programming mistake.
	 * The program will throw and IndexOutOfBoundsException
	 */
	@Test
	public void cancelUnexistingBookingTest() {
		Booking booking = bookings.get(5);
		cancelBooking(booking);
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

		return c.getTime();
	}

}
