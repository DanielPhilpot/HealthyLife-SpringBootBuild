package HealthyLife.SpringBootBuild.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.ResultSet;

public class User {
	
	private Connection connection=null;
	private String username;
	private String sex;
	
	private String dietReqs[] = new String[7];
	private boolean isVegiterian = false;
	private boolean isVegan = false;
	private boolean isLactoseInt = false;
	private boolean isGlutenInt = false;
	private boolean isNutAlerg = false;
	private boolean isKosher = false;
	private boolean isHalal = false;
	
	private int gymDistance;
	private int parkDistance;
	
	private HashMap <String, Meal> meals;
	private HashMap <String, ScheduleItem> scheduleItems;
	
	
	String connectionURL = "jdbc:mysql://localhost:3306/healthy_life";
	
	public User(){
		try {
			
			meals = new HashMap<String, Meal>();
			scheduleItems = new HashMap<String, ScheduleItem>();
			
            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");

            // Get a Connection to the database
            // TODO 1 put in your own password.
            connection = DriverManager.getConnection(connectionURL, "root", "#Bebo123");
            
            }catch(Exception e){

                System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
            }
	}
	
	public void cleanUp() {
        try {
            connection.close(); 
            connection = null;
        }catch(Exception e){
                System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
    }
	
	public boolean validateUser(String username, String password) {
		try {
			String query = "SELECT username, password, sex, dietReqs, gymDistance, parkDistance FROM users WHERE username = '" + username + "';";
			
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) { 
            	String dbUser = rs.getString(1);
            	String dbPass = rs.getString(2);
            	String dbSex = rs.getString(3);
            	String dbDiet = rs.getString(4);
            	String dbGym = rs.getString(5);
            	String dbPark = rs.getString(6);
            	
            	if(dbPass.equals(password)){
            		
            		//Debugging
            		System.out.println(dbUser + " ---> " + dbPass);
            		
            		this.username = username;
            		sex = dbSex;
            		this.setDietReqs(dbDiet);
            		
            		return true;
            		
            	} else {
            		
            		//Debugging
            		System.out.println("login failed");
            		System.out.println(username);
            		System.out.println(dbUser);
            		System.out.println(password);
            		System.out.println(dbPass);
            		
            		return false;
            		
            	}
            } 
		}
        catch(SQLException e) {
        System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
		
		return false;
	}
	
	public String signUp(String newUsername, String newPassword, String newSex) {
		String sex; if(newSex.equals("Male")) {sex = "m";} else {sex = "f";};
		System.out.println(newSex);
		System.out.println(sex);
		
		try {
			String update = "INSERT INTO users VALUES (NULL,'" + newUsername + "', '" + newPassword + "', '" + sex + "',null,null,null)";
			System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
            
            this.username = newUsername;
            this.sex = sex;
            
            return "Sucess";
            
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
			if(e.getMessage().contains("Duplicate entry")) {
				return "Username in use";
			} return "Other Error";
        }
	}
	
	public void logOut() {
		username = null;
		sex = null;
		gymDistance = 0;
		parkDistance = 0; 
		this.setDietReqs("");
		meals.clear();
		scheduleItems.clear();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setGender() {
		if(sex == null ) {sex = "m";};
	}
	
	public String getSex() {
		if(sex.equals("f")) {
			String x = "Female";
			return x;
		} else {
			String x = "Male";
			return x;
		}
	}
	
	public int getGymDistance() {
		return gymDistance;
	}
	
	public void setGymDistance(String hours, String mins) {
		int distance = Integer.parseInt(mins) + Integer.parseInt(hours) * 60;
		
		try {
			String update = "UPDATE healthy_life.users SET gymDistance = '" + distance + "' WHERE username = '" + username + "'";
			System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
            
            gymDistance = distance;
            
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
		
	}
	
	public int getParkDistance() {
		return parkDistance;
	}
	
	public void setParkDistance(String hours, String mins) {
		int distance = Integer.parseInt(mins) + Integer.parseInt(hours) * 60;
		
		try {
			String update = "UPDATE healthy_life.users SET parkDistance = '" + distance + "' WHERE username = '" + username + "'";
			System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
            
            parkDistance = distance;
            
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
	}
	
	
	
	
	
	
	//----------DIETARY REQUIRMENTS CODE -----------------
	
	public void setDietReqs(String reqs) {
		isVegiterian = false;
		isVegan = false;
		isLactoseInt = false;
		isGlutenInt = false;
		isNutAlerg = false;
		isKosher = false;
		isHalal = false;
		
		//System.out.println(reqs);
		
		dietReqs = reqs.split(",");		
		
		for(int i = 0; i < dietReqs.length; i++) {
			switch(dietReqs[i]) {
				case "V":
					isVegiterian = true;
					break;
				case "VV":
					isVegan = true;
					break;
				case "L":
					isLactoseInt = true;
					break;
				case "G":
					isGlutenInt = true;
					break;
				case "N":
					isNutAlerg = true;
					break;
				case "K":
					isKosher = true;
					break;
				case "H":
					isHalal = true;
					break;
			}
			
		}
	}
	
	public String[] getDietRqs() {
		return dietReqs;
	}
	

	
	public void submitDietReqs(String dietReqString) {
		try {
			String update = "UPDATE healthy_life.users SET dietReqs = '" + dietReqString + "' WHERE username = '" + username + "'";
			//System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
            
            this.setDietReqs(dietReqString);
            
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
	}
	
	public boolean getIsVegiterian() {
		if(isVegiterian) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsVegan() {
		if(isVegan) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsLactoseInt() {
		if(isLactoseInt) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsGlutenInt() {
		if(isGlutenInt) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsNutAlerg() {
		if(isNutAlerg) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsKosher() {
		if(isKosher) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsHalal() {
		if(isHalal) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	//-------------Get Events code-----------------------
	
	public HashMap<String, Meal> getMeals() {
		return meals;
	}
	
	public void addToMeals(String id, Meal m) {
		meals.put(id, m);
	}
	
	public void getUserMeals() {
		try {
			String query = "SELECT * FROM healthy_life.events WHERE username = '"+ username + "' AND eventType = 'meal'";
			System.out.println(query);
			
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while(rs.next()) {
            	Meal m = new Meal();
            	m.collectEventProperties(rs.getInt(1), rs.getString(3));
            		
            	meals.put(rs.getString(1), m);
            	
            	String query2 = "SELECT * FROM healthy_life.meals WHERE eventId = '"+ rs.getInt(1) + "'";
    			System.out.println(query);
    			
    			Statement stmt2 = connection.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2);
    			
                if(rs2.next()) {
                	m.setTitle(rs2.getString(3));
                	m.setDescription(rs2.getString(4));
                	m.setLocation(rs2.getString(5));
                	m.setMealType(rs2.getString(6));
                	m.setWeight(rs2.getString(7));
                	m.setCalories(rs2.getString(8));
                	m.setFv(rs2.getString(9));
                	m.setCarbs(rs2.getString(10));
                	m.setProtein(rs2.getString(11));
                	m.setDairy(rs2.getString(12));
                	m.setOils(rs2.getString(13));
                	m.setJunk(rs2.getString(14));
                }

            }
           } catch(SQLException e) {
               System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
           }
   		
	}
	
	
	//-------------------Schedule Items Code--------------------------
	
	public HashMap<String, ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}
	
	public void addToScheduleItems(String id, ScheduleItem s) {
		scheduleItems.put(id, s);
	}
	
	public void getUserSchedule() {
		
		try {
			String query = "SELECT * FROM healthy_life.scheduleitems WHERE username = '"+ username + "'";
			System.out.println(query);
			
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
            	ScheduleItem s = new ScheduleItem();
            	s.collectItemProperties(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
            	scheduleItems.put(rs.getString(1), s);
            }
            
		} catch(SQLException e) {
            System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
	}
	
}
