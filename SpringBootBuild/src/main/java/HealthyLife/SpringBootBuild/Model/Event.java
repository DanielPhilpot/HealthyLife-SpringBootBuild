package HealthyLife.SpringBootBuild.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Event {
	
	private Connection connection=null;
	
	String eventDateTime;
	String eventType;
	int eventId;
	
	String connectionURL = "jdbc:mysql://localhost:3306/healthy_life";
	
	
	public Event() {
		try {

            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");

            // Get a Connection to the database
            // TODO 1 put in your own password.
            connection = DriverManager.getConnection(connectionURL, "root", "#Bebo123");
            
            }catch(Exception e){

                System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
            }
	}
	
	public int setEventProperties(String username, String dateTime) {
		
		try {
			String update = "INSERT INTO events VALUES (NULL,'" + username + "', '" + dateTime + "', '" + eventType + "')";
			System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            eventDateTime = dateTime;
            
            if(rs.next()) {
            	eventId = rs.getInt(1);;
            	return eventId;
            } else {
            	return 0;
            }
                        
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
			return 0;
        }
	}
	
	public void setEventSpecificProperties(String query) {
	
	}
	
	public void collectEventProperties(int eventId, String dateTime) {
		this.eventId = eventId;
		eventDateTime = dateTime;
	}
	
	
	public void setEventDate(String dateTimeString) {
		eventDateTime = dateTimeString;			
	}
	public String getEventDateTime() {
		return eventDateTime;
	}
	public String getEventType() {
		return eventType;
	}
	public int getEventId() {
		return eventId;
	}

}
