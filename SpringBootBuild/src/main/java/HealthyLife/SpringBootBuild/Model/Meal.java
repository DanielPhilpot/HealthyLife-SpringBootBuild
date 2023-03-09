package HealthyLife.SpringBootBuild.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Meal extends Event {

	private Connection connection=null;
	private String title;
	private String description;
	private String location;
	private String mealType;
	private String weight;
	private String calories;
	private String fv;
	private String carbs;
	private String protein;
	private String dairy;
	private String oils;
	private String junk;
	
	public Meal() {
		// TODO Auto-generated constructor stub
		eventType = "meal";
		
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
	
	public void setEventSpecificProperties(int eventId, String title, String description, String location, String mealType, String weight, String calories, String fv, String carbs, String protein, String dairy, String oils, String junk) {
		try {
			String update = "INSERT INTO meals VALUES (null,"+eventId+", '"+title+"', '"+description+"', '"+location+"', '"+mealType+"', "+weight+", "+calories+", "+fv+", "+carbs+", "+protein+", "+dairy+", "+oils+", "+junk+");";
			System.out.println(update);
			
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
             
            this.setTitle(title);
            this.setDescription(description);
            this.setLocation(location);
            this.setMealType(mealType);
            this.setWeight(weight);
            this.setCalories(calories);
            this.setFv(fv);
            this.setCarbs(carbs);
            this.setProtein(protein);
            this.setDairy(dairy);
            this.setOils(oils);
            this.setJunk(junk);
            
            
		} catch(SQLException e) {
			System.out.println("Exception is ;"+e + ": message is " + e.getMessage());
        }
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getFv() {
		return fv;
	}

	public void setFv(String fv) {
		this.fv = fv;
	}

	public String getCarbs() {
		return carbs;
	}

	public void setCarbs(String carbs) {
		this.carbs = carbs;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getDairy() {
		return dairy;
	}

	public void setDairy(String dairy) {
		this.dairy = dairy;
	}

	public String getOils() {
		return oils;
	}

	public void setOils(String oils) {
		this.oils = oils;
	}

	public String getJunk() {
		return junk;
	}

	public void setJunk(String junk) {
		this.junk = junk;
	}

}
