package dboperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class QueryTest {
	
	@Before 
	public void init(){
		
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
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String format = "%20s%20s\n";
		try {
			
			
			String sqlQuery = "SELECT accomodation.type as room, room_fair.value as price "
					+ "FROM accomodation_fair_relation "
					+ "INNER JOIN accomodation ON accomodation_fair_relation.id_accomodation = accomodation.id "
					+ "INNER JOIN room_fair ON accomodation_fair_relation.id_room_fair=room_fair.id";
					ps = conn.prepareStatement(sqlQuery);

					rs = ps.executeQuery();
		    
		    boolean hasResults = rs.next();
		     
		    if (hasResults) {
		        System.out.format(format, "room", "price");
		        do {
		            System.out.format(format, rs.getString("room"), rs.getDouble("price"));
		        } while (rs.next());
		    } else {
		        System.out.println("No results");
		    }
		} catch (SQLException e) {
		    System.err.println("Cannot execute query: " + e.getMessage());
		} finally {
		    if (rs != null) try { rs.close(); } catch (SQLException e) {  }
		    if (ps != null) try { ps.close(); } catch (SQLException e) {  }
		    try { conn.close(); } catch (SQLException e) { }
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

}
