package HealthyLife.SpringBootBuild.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleItem {
	
	private Connection connection=null;
	
	private String itemType;
	private String snackOrLocation;
	private int day;
	private String time;
	private String duration;
	private int itemId;
	
	private boolean isFood;
	private boolean isExercise;
	private boolean isWorkSleep;
	
	String connectionURL = "jdbc:mysql://localhost:3306/healthy_life";
	
	public ScheduleItem() {
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
	
	
	public int setItemProperties(String username, String itemType, String sOrL, int day, String time, String duration) {
		try {
			String update = "INSERT INTO scheduleItems VALUES (NULL,'" + username + "', '" + itemType + "', '" + sOrL + "', '" + day + "', '" + time + "', '" + duration + "')";
			System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            this.itemType = itemType;
            this.snackOrLocation = sOrL;
            this.day = day;
            this.time = time;
            this.duration = duration;
            
            this.setBools();
            
            if(rs.next()) {
            	itemId = rs.getInt(1);
            	return itemId;
            } else {
            	return 0;
            }
            
                        
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
			return 0;
        }
	}
	
	public void collectItemProperties(int id, String type, String SoL, int day, String time, String duration) {
		
		this.itemId = id;
		this.itemType = type;
		this.snackOrLocation = SoL;
		this.day = day;
		this.time = time;
		this.duration = duration;
		
		this.setBools();
	}
	
	public void setBools() {
		switch(itemType) {
    		case "food":
    			isFood = true;
    		case "exercise":
    			isExercise = true;
    		case "worksleep":
    			isWorkSleep = true;
    }
	}
	
	public void deleteItem() {
		//TODO
	}
	
	

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSnackOrLocation() {
		return snackOrLocation;
	}

	public void setSnackOrLocation(String snackOrLocation) {
		this.snackOrLocation = snackOrLocation;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public boolean getIsFood() {
		return isFood;
	}


	public void setFood(boolean isFood) {
		this.isFood = isFood;
	}


	public boolean getIsExercise() {
		return isExercise;
	}


	public void setExercise(boolean isExercise) {
		this.isExercise = isExercise;
	}


	public boolean getIsWorkSleep() {
		return isWorkSleep;
	}


	public void setWorkSleep(boolean isWorkSleep) {
		this.isWorkSleep = isWorkSleep;
	}

}
