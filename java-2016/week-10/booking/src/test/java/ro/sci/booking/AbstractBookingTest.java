package ro.sci.booking;

import java.util.List;

/**
 * Abstractization for all tests holding common stuff.
 * @author alex
 *
 */
public abstract class AbstractBookingTest {

	protected static final String IO_FOLDER = "/opt/jde/workspace/scij-emilia/scoala-informala-homeworks/java-2016/week-10/booking/files/";

	protected List<Accommodation> rooms;

	protected List<BookingPeriod> periods;

	protected List<Booking> bookings;

}
