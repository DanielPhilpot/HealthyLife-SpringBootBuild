package HealthyLife.SpringBootBuild.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="scheduleitems")
public class ScheduleItemEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username", length=20, nullable=false)
	private String username;
	
	@Column (name="itemtype", length=9)
	private String itemtype;
	
	@Column (name="snackorlocation", length=5)
	private String snackorlocation;
	
	@Column (name="day")
	private Integer day;
	
	@Column (name = "time", columnDefinition = "TIME")
	private java.sql.Time time;
	
	@Column (name = "duration", columnDefinition = "TIME")
	private java.sql.Time duration;
	
	public Integer getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getItemType() {
		return itemtype;
	}
	
	public void setItemType(String itemType) {
		this.itemtype = itemType;
	}
	
	public String getSnackOrLocation() {
		return snackorlocation;
	}
	
	public void setSnackOrLocation(String snackOrLocation) {
		this.snackorlocation = snackOrLocation;
	}
	
	public Integer getDay() {
		return day;
	}
	
	public void setDay(Integer day) {
		this.day = day;
	}
	
	public java.sql.Time getTime(){
		return time;
	}
	
	public void setTime(java.sql.Time time) {
		this.time = time;
	}
	
	public java.sql.Time getDuration() {
		return duration;
	}
	
	public void setDuration(java.sql.Time duration) {
		this.duration = duration;
	}
	
}
