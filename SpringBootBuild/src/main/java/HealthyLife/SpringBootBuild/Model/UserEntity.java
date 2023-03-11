package HealthyLife.SpringBootBuild.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username", length=20, nullable=false, unique=true)
	private String username;
	
	@Column (name="password", length=10, nullable=false)
	private String password;
	
	@Column (name="sex", length=1, nullable=false)
	private String sex;
	
	@Column (name="dietreqs", length=14)
	private String dietreqs;
	
	@Column (name="gymdistance")
	private Integer gymdistance;
	
	@Column (name="pakrdistance")
	private Integer parkdistance;
	
	public Integer getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getDietRqs() {
		return dietreqs;
	}
	
	public void setDietReqs(String dietReqs) {
		this.dietreqs = dietReqs;
	}
	
	public int getGymDistance() {
		return gymdistance;
	}
	
	public void setGymDistance(Integer gymDistance) {
		this.gymdistance = gymDistance;
	}
	
	public int getParkDistance() {
		return parkdistance;
	}
	
	public void setParkDistance(Integer parkDistance) {
		this.parkdistance = parkDistance;
	}
}

