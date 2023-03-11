package HealthyLife.SpringBootBuild.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="meals")
public class MealEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="eventid", nullable=false)
	private Integer eventid;
	
	@Column(name="title", length=30)
	private String title;
	
	@Column(name="description", columnDefinition = "MEDIUM TEXT")
	private String description;
	
	@Column(name="location", length=30)
	private String location;
	
	@Column(name="mealtype", length=5)
	private String mealtype;
	
	@Column(name="weight")
	private Integer weight;
	
	@Column(name="calories")
	private Integer calories;
	
	@Column(name="fv")
	private Integer fv;
	
	@Column(name="carbs")
	private Integer carbs;
	
	@Column(name="protein")
	private Integer protein;
	
	@Column(name="dairy")
	private Integer dairy;
	
	@Column(name="oil")
	private Integer oil;
	
	@Column(name="junk")
	private Integer junk;
	
	public Integer getId() {
		return id;
	}
	
	public Integer getEventId() {
		return eventid;
	}
	
	public void setEventId(Integer eventId) {
		this.eventid = eventId;
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
		return mealtype;
	}
	
	public void setMealType(String mealType ) {
		this.mealtype = mealType;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getCalories() {
		return calories;
	}
	
	public void setCalories(Integer calories) {
		this.calories = calories;
	} 
	
	public Integer getFV() {
		return fv;
	}
	
	public void setFV(Integer FV) {
		this.fv = FV;
	}
	
	public Integer getCarbs() {
		return carbs;
	}
	
	public void setCarbs(Integer carbs) {
		this.carbs = carbs;
	}
	
	public Integer getProtein() {
		return protein;
	}
	
	public void setProtein(Integer protein) {
		this.protein = protein;
	}
	public Integer getDairy() {
		return dairy;
	}
	
	public void setDairy(Integer dairy) {
		this.dairy = dairy;
	}
	public Integer getOil() {
		return oil;
	}
	
	public void setOil(Integer oil) {
		this.oil = oil;
	}
	
	public Integer getJunk() {
		return junk;
	}
	
	public void setJunk(Integer junk) {
		this.junk = junk;
	}
	
}
