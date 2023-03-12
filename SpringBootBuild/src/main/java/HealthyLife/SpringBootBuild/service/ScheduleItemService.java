package HealthyLife.SpringBootBuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HealthyLife.SpringBootBuild.Model.ScheduleItemEntity;
import HealthyLife.SpringBootBuild.repository.ScheduleItemRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class ScheduleItemService {
	
	@Autowired
	private ScheduleItemRepository scheduleItemRepo;
	
	public void getUserSchedule(String username, HttpSession session) {
		List<ScheduleItemEntity> userSchedule = scheduleItemRepo.findByusername(username);
		session.setAttribute("schedule", userSchedule);
	}
	
	public void createScheduleItem(String username, String itemType, String snackOrLocation, Integer day, java.sql.Time time, java.sql.Time duration) {
		ScheduleItemEntity newItem = new ScheduleItemEntity();
		newItem.setUsername(username);
		newItem.setItemType(itemType);
		newItem.setSnackOrLocation(snackOrLocation);
		newItem.setDay(day);
		newItem.setTime(time);
		newItem.setDuration(duration);
		
		try {
			scheduleItemRepo.save(newItem);
			System.out.println("ITEM CREATION SUCESS");
		}
		catch(Exception e) {
			System.out.println("ITEM CREATION FAILED");
		}
	}
}
