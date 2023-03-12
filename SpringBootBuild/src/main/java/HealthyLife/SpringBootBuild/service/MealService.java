package HealthyLife.SpringBootBuild.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HealthyLife.SpringBootBuild.Model.EventEntity;
import HealthyLife.SpringBootBuild.Model.MealEntity;
import HealthyLife.SpringBootBuild.repository.MealRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class MealService {

	@Autowired
	private MealRepository MealRepo;
	
	public void createMeal(Integer eventId, String username, String title, String description, String location, String mealType, Integer weight, Integer calories, Integer fv, Integer carbs, Integer protein, Integer dairy, Integer oil, Integer junk) {
		MealEntity newMeal = new MealEntity();
		
		newMeal.setEventId(eventId);
		newMeal.setUsername(username);
		newMeal.setTitle(title);
		newMeal.setDescription(description);
		newMeal.setLocation(location);
		newMeal.setMealType(mealType);
		newMeal.setWeight(weight);
		newMeal.setCalories(calories);
		newMeal.setFV(fv);
		newMeal.setCarbs(carbs);
		newMeal.setProtein(protein);
		newMeal.setDairy(dairy);
		newMeal.setOil(oil);
		newMeal.setJunk(junk);
		
		try {
			MealRepo.save(newMeal);
			System.out.println("Meal CREATION SUCESS");
		} catch(Exception e) {
			System.out.println("Meal CREATION FAILED");
		}
	}
	
	public void getUserMeals(String username, HttpSession session) {
		List<MealEntity> userMeals = MealRepo.findByusername(username);
		
		session.setAttribute("userMeals", userMeals);
	}
}