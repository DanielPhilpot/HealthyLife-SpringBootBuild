package HealthyLife.SpringBootBuild.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="events")
public class EventEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username", length=20, nullable=false)
	private String username;
	
	@Column(name="datetime", columnDefinition = "DATETIME")
	private java.sql.Timestamp datetime;
	
	@Column(name="eventtype", length=9)
	private String eventtype;
	
	public Integer getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public java.sql.Timestamp getDateTime(){
		return datetime;
	}
	
	public void setDateTime(java.sql.Timestamp dateTime) {
		this.datetime = dateTime;
	}
	
	public String getEventType(){
		return eventtype;
	}
	
	public void setEventType(String eventType) {
		this.eventtype = eventType;
	}
}
