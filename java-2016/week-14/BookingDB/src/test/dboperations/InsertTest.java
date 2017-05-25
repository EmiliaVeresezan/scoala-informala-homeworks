package dboperations;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class InsertTest {
	
	List<Accommodation> rooms;
	List<RoomFair> roomFairs;
	Connection conn;
	
	@Before
	public void init() {
		rooms = new ArrayList<Accommodation>();
		roomFairs = new ArrayList<RoomFair>();
		
		Date from = getDate(2016, 8, 28);
		Date to = getDate(2016, 10, 31);
		Season season = createSeason(SeasonType.LOW, from, to);
		
		RoomFair roomFair1 = createRoomFair(90.5, season);
		roomFairs.add(roomFair1);
		Accommodation room1 = createNewRoom(1, AccommodationType.SUITE, roomFair1, BedType.SINGLE, 5);
		rooms.add(room1);
		
		RoomFair roomFair2 = createRoomFair(80, season);
		roomFairs.add(roomFair2);
		Accommodation room2 = createNewRoom(2,AccommodationType.ROYAL, roomFair2, BedType.QUEEN_SIZE, 2);
		rooms.add(room2);
		
		RoomFair roomFair3 = createRoomFair(100.50, season);
		roomFairs.add(roomFair3);
		Accommodation room3 = createNewRoom(3, AccommodationType.ROYAL, roomFair2, BedType.KING_SIZE, 2);
		rooms.add(room3);		
		
					
	}
	
	@Test
	public void test() {
		
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = connect("postgresql", "localhost", 5432, "booking", "postgres", "emilia");
		if (conn == null) return;
	
		
		PreparedStatement psAccomodation = null;
		PreparedStatement psRoomFair = null;
		PreparedStatement psRelation = null;
	
		String sqlInsertAccomodation = "INSERT INTO accomodation (id, type, bed_type, max_guests, description) "
				+ "VALUES (?,?,?,?,?)";
		String sqlInsertRoomFair = "INSERT INTO room_fair (id, value, season)"
				+ "VALUES (?,?,?)";
		String sqlInsertRelation = "INSERT INTO accomodation_fair_relation (id, id_accomodation, id_room_fair)"
				+ "VALUES (?,?,?)";
				
		try {
			conn.setAutoCommit(false);
		
			psAccomodation = conn.prepareStatement(sqlInsertAccomodation);
		    psRoomFair = conn.prepareStatement(sqlInsertRoomFair);
		    psRelation = conn.prepareStatement(sqlInsertRelation);

		    for (int i=0;i<rooms.size(); i++){
		    	int pKeyAccomodation=rooms.get(i).getRoomId();
		    	String type = rooms.get(i).getType().toString();
		    	String bed_type=rooms.get(i).getBedType().toString();
		    	int max_guests = rooms.get(i).getMaxGuests();
		    	String description = rooms.get(i).getDescription();
		    	insertRecordIntoAccomodation(psAccomodation, pKeyAccomodation,type, bed_type, max_guests, description);
		    	
		    	int pKeyRoomFair=i;
		    	double value = roomFairs.get(i).getValue();
		    	String season = roomFairs.get(1).getSeason().toString();
		    	insertRecordIntoRoomFair(psRoomFair, pKeyRoomFair, value, season);
		    	
		    	int pKeyRelation = i;
		    	insertRecordIntoRelationTable(psRelation, pKeyRelation, pKeyAccomodation, pKeyRoomFair);
		
			conn.commit();
		    }
		    
		} catch (SQLException e) {
		    System.err.println(e.getMessage());
		    e.printStackTrace();
		    if (conn !=null) {
		    	try {
		    		System.err.print("Transaction is being rolled back.");
		    		conn.rollback();
		    	} catch (SQLException ex) {
		    		e.printStackTrace();
		    	}
		    }
		} finally {
			if (psAccomodation !=null){
				try {
					psAccomodation.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psRoomFair !=null){
				try {
					psRoomFair.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psRelation !=null){
				try {
					psRelation.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void insertRecordIntoAccomodation(PreparedStatement psAccomodation, int pKeyAccomodation, String type, String bed_type, int max_guests, String description){	
	    try {
			psAccomodation.setInt(1, pKeyAccomodation);
			psAccomodation.setString(2, type);
			psAccomodation.setString(3, bed_type);
			psAccomodation.setInt(4, 2);
		    psAccomodation.setString(5, description);
		    psAccomodation.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertRecordIntoRoomFair(PreparedStatement psRoomFair, int pKeyRoomFair,double value, String season){
	    try {
			psRoomFair.setInt(1,pKeyRoomFair);
			psRoomFair.setDouble(2, value);
			psRoomFair.setString(3,season);
			psRoomFair.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertRecordIntoRelationTable(PreparedStatement psRelation, int pKeyRelation, int foreignKeyAccom, int foreignKeyRoomFair){
	    try {
			psRelation.setInt(1, pKeyRelation);
		    psRelation.setInt(2, foreignKeyAccom);
		    psRelation.setInt(3, foreignKeyRoomFair);
		    psRelation.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	   
	
	private static Connection connect(String type, String host, int port, String dbName, String user, String pw) {

		Connection conn = null;
		DriverManager.setLoginTimeout(60);
		try {
			String url = new StringBuilder().append("jdbc:").append(type) 
					.append("://").append(host).append(":").append(port).append("/").append(dbName).append("?user=")
					.append(user).append("&password=").append(pw).toString();
			
			System.out.println("URL:" + url);
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println("Cannot connect to the database: " + e.getMessage());
		}

		return conn;
	}
	
	public void destroy(){
		rooms = null;
		roomFairs = null;
	}

	/**
	 * Creates a new instance of Accommodation.
	 * @param id 
	 * @param type     the AccommodationType to be created
	 * @param fair     the price of the Accommodation
	 * @param bedType  the type of bed the accommodation contains 
	 * @param guests   the maximum number of guests for the accommodation
	 * @return		   an Accommodation object	
	 */
	private Accommodation createNewRoom(int id, AccommodationType type, RoomFair fair, BedType bedType, int guests) {
		Accommodation accomodation = new Accommodation();
		accomodation.setRoomId(id);
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
