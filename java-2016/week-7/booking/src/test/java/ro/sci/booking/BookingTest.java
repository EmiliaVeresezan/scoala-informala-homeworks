package ro.sci.booking;

import static org.junit.Assert.*;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Test class for cancel booking functionality.
 * @author Emilia
 *
 */
public class BookingTest {

	List<Accommodation> rooms;

	List<BookingPeriod> periods;

	List<Booking> bookings;

	/**
	 * Initializes the components used to test the functionality: a list of rooms, a list of time periods and a list of bookings.
	 * The rooms are represented as Accommodation objects, periods are represented as Period objects, and bookings are represented as Booking objects.
	 * A Booking object is composed of an Accommodation object and a Period object. Therefore, a room can be associated with multiple booking periods by creating
	 * multiple Booking objects. 
	 */
	@Before
	public void init() {
		
		rooms = new ArrayList<Accommodation>();
		
		Date from = getDate(2016, 8, 28);
		Date to = getDate(2016, 10, 31);
		Season season = createSeason(SeasonType.LOW, from, to);
		
		RoomFair roomFair1 = createRoomFair(90, season);
		Accommodation room1 = createNewRoom(AccommodationType.SUITE, roomFair1);
		rooms.add(room1);
		
		periods = new ArrayList<BookingPeriod>();
		
		periods.add(new BookingPeriod(getDate(2016,9,1), getDate(2016,9,5)));
		periods.add(new BookingPeriod(getDate(2016,9,9), getDate(2016,9,12)));
		periods.add(new BookingPeriod(getDate(2016,10,13), getDate(2016,10,20)));
		periods.add(new BookingPeriod(getDate(2016,9,21), getDate(2016,9,24)));
				
		bookings = new ArrayList<Booking>();

		
		bookings.add(new Booking(room1, periods.get(0)));
		bookings.add(new Booking(room1, periods.get(1)));
		bookings.add(new Booking(room1, periods.get(2)));
		bookings.add(new Booking(room1, periods.get(3)));
		
	}
	
	/**
	 * Tests all the possible cases in which the given period is not available for booking.
	 * The tested method returns true when the given period has already been booked partially or completely.
	 */
	@Test
	public void findAccomodationNotAvailableTest(){
		
		//period is not available for booking for room1- both from and to dates are included in a booked period
		Date from = getDate(2016,9, 22);
		Date to = getDate(2016, 9, 23);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
	
		//period is not available for booking for room1 - from date is included in a booked period, to date is not
		from = getDate(2016,9, 22);
		to = getDate(2016, 9, 25);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
		//period is not available for booking for room1 - to date is included in a booked period, from date is not
		from = getDate(2016,9, 20);
		to = getDate(2016, 9, 23);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
		//period is not available for booking for room1 - from date is equal to first day of booked period
		from = getDate(2016,9, 21);
		to = getDate(2016, 9, 24);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));					
			
		//period is not available for booking for room1 - from date is before the first day of the booked period and 
		// the to date is after the last day of the booked period
		from = getDate(2016,9, 20);
		to = getDate(2016, 9, 25);
		assertTrue(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
	}
	
	/**
	 * Tests all the possible cases in which the given period is available for booking.
	 * The tested method returns false when the given period is not already booked partially or completely.
	 */
	@Test
	public void findAccomodationAvailableTest(){
		
		//period is available for booking for room1 - from and to dates are after the booked period
		Date from = getDate(2016,9, 25);
		Date to = getDate(2016, 9, 26);
		assertFalse(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));
		
		//period is available for booking for room1 - from and to dates are before the booked period
		from = getDate(2016,9, 16);
		to = getDate(2016, 9, 20);
		assertFalse(findAccomodationTypeByPeriod(AccommodationType.SUITE, from, to));		
		
		//period is available for booking for room1 - from date is equal to last day of booked period
		//and to date is after the last day of the booked period
		from = getDate(2016,9, 24);
		to = getDate(2016, 9, 26);
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
		Date from = getDate(2016, 9, 15);
		Date to = getDate(2016, 9, 19);
		
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
		assertEquals(4, bookings.size());
		assertEquals(4, periods.size());
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
	 * Creates a new instance of Accommodation.
	 * @param type    the AccommodationType to be created
	 * @param fair    the price of the Accommodation
	 * @return		  an Accommodation object	
	 */
	private Accommodation createNewRoom(AccommodationType type, RoomFair fair) {
		Accommodation accomodation = new Accommodation();
		accomodation.setType(type);
		accomodation.setFair(fair);
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
