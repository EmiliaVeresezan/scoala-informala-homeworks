package ro.sci.booking;

import java.util.Date;

public class Season {
	private Date from;
	private Date to;
	private SeasonType type;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public SeasonType getType() {
		return type;
	}

	public void setType(SeasonType type) {
		this.type = type;
	}
}
