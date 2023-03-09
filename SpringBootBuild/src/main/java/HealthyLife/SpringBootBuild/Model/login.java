package HealthyLife.SpringBootBuild.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class login {
	
	private Connection connection=null;
	
	String connectionURL = "jdbc:mysql://localhost:3306/healthy_life";
	
	public login() {
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
			String query = "SELECT username, password FROM users WHERE username = '" + username + "';";
			
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) { 
            	String dbUser = rs.getString(1);
            	String dbPass = rs.getString(2);
            	if(dbPass.equals(password)){
            		
            		//Debugging
            		System.out.println(dbUser + " ---> " + dbPass);
            		
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
}
