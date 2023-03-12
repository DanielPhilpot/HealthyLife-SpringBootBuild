package HealthyLife.SpringBootBuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HealthyLife.SpringBootBuild.Model.EventEntity;
import HealthyLife.SpringBootBuild.repository.EventRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class EventService {

	@Autowired
	private EventRepository EventRepo;
	
	public int createEvent(String username, java.sql.Timestamp dateTime, String eventType) {
		EventEntity newEvent = new EventEntity();
		
		newEvent.setUsername(username);
		newEvent.setDateTime(dateTime);
		newEvent.setEventType(eventType);
		
		try {
			EventRepo.save(newEvent);
			System.out.println("Event CREATION SUCESS");
			return newEvent.getId();
		} catch(Exception e) {
			System.out.println("EVENT CREATION FAILED");
			return 0;
		}
	}
	
	public void getUserEvents(String username, HttpSession session) {
		List<EventEntity> userEvents = EventRepo.findByusername(username);
		session.setAttribute("events", userEvents);
	}
}
