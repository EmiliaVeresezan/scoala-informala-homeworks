package dboperations;

import java.util.Date;

public class BookingPeriod {

	private Date from;
	private Date to;

	public BookingPeriod (){
		
	}
	public BookingPeriod(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public void setTo(Date to) {
		this.to = to;
	}
}
