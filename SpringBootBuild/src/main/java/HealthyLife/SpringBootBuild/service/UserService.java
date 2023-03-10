package HealthyLife.SpringBootBuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HealthyLife.SpringBootBuild.Model.UserEntity;
import HealthyLife.SpringBootBuild.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void add(UserEntity user) {
		userRepository.save(user);
	}
	
	public UserEntity get(String username) {
		UserEntity currentUser = userRepository.findByusername(username).get(0);
		return currentUser;
	}
	
	public boolean validateUser(String username, String password, HttpSession session) {
		UserEntity currentUser = this.get(username);
		System.out.println(currentUser.getUsername());
		System.out.println(username);
		System.out.println(currentUser.getPassword());
		System.out.println(password);
		if(currentUser.getPassword().equals(password)) {
			session.setAttribute("user", currentUser);
			System.out.println("LOGIN SUCESS");
			return true;
		} else {
			System.out.println("LOGIN FAILED");
			return false;
		}
	}
	
	
	
	
	
}
