package ro.sci.booking;

public class Booking {

	private Accommodation room;
	private BookingPeriod bookingPeriod;
	// customer
	// id
	// price
	// status

	public Booking(Accommodation room, BookingPeriod bookingPeriod) {
		this.setRoom(room);
		this.setBookingPeriod(bookingPeriod);
	}

	public Accommodation getRoom() {
		return room;
	}

	public void setRoom(Accommodation room) {
		this.room = room;
	}

	public BookingPeriod getBookingPeriod() {
		return bookingPeriod;
	}

	public void setBookingPeriod(BookingPeriod bookingPeriod) {
		this.bookingPeriod = bookingPeriod;
	}

}
