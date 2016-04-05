package ro.sci.booking;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for availability check.
 * @author alex
 *
 */
public class TestAvailability {

	List<Accommodation> rooms;

	List<BookingPeriod> periods;

	List<Booking> bookings;

	@Before
	public void init() {
		rooms = new ArrayList<Accommodation>();

		Date from = getDate(2016, 5, 1);
		Date to = getDate(2016, 8, 27);
		Season season = createSeason(SeasonType.HIGH, from, to);

		RoomFair roomFair1 = createRoomFair(100, season);
		Accommodation room1 = createNewRoom(AccommodationType.ROYAL, roomFair1);
		rooms.add(room1);

		periods = new ArrayList<BookingPeriod>();

		periods.add(new BookingPeriod(getDate(2016, 5, 12), getDate(2016, 5, 17)));
		periods.add(new BookingPeriod(getDate(2016, 6, 10), getDate(2016, 6, 13)));
		periods.add(new BookingPeriod(getDate(2016, 7, 5), getDate(2016, 7, 12)));
		periods.add(new BookingPeriod(getDate(2016, 8, 2), getDate(2016, 8, 4)));

		bookings = new ArrayList<Booking>();

		bookings.add(new Booking(room1, periods.get(0)));
	}

	@Test
	public void testPeriod() {
		Date from = getDate(2016, 7, 15);
		Date to = getDate(2016, 7, 20);

		assertFalse(findAccomodationTypeByPeriod(AccommodationType.ROYAL, from, to));
	}

	private boolean findAccomodationTypeByPeriod(AccommodationType type, Date from, Date to) {
		boolean found = false;
		for (Booking booking : bookings) {
			found = booking.getRoom().getType().equals(type) && booking.getBookingPeriod().getFrom().before(from)
					&& booking.getBookingPeriod().getTo().after(to);
			if (found) {
				break;
			}
		}
		return found;
	}

	private Accommodation createNewRoom(AccommodationType type, RoomFair fair) {
		Accommodation accomodation = new Accommodation();
		accomodation.setType(type);
		accomodation.setFair(fair);
		return accomodation;
	}

	private RoomFair createRoomFair(int i, Season season) {
		RoomFair roomFair = new RoomFair();
		roomFair.setSeason(season);
		roomFair.setValue(i);
		return roomFair;
	}

	private Season createSeason(SeasonType type, Date from, Date to) {
		Season season = new Season();
		season.setFrom(from);
		season.setTo(to);
		season.setType(type);
		return season;
	}

	private Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		return c.getTime();
	}

}
