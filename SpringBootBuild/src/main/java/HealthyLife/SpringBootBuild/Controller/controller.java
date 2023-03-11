package HealthyLife.SpringBootBuild.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import HealthyLife.SpringBootBuild.service.EventService;
import HealthyLife.SpringBootBuild.service.MealService;
import HealthyLife.SpringBootBuild.service.ScheduleItemService;
import HealthyLife.SpringBootBuild.service.UserService;
import HealthyLife.SpringBootBuild.Model.UserEntity;


@Controller
public class controller {
	
	private UserEntity currentUser; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScheduleItemService scheduleItemService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private MealService mealService;
	
	@RequestMapping("/")
	public ModelAndView welcomePage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("user", session.getAttribute("user"));
		model.addObject("welcomeMessage", "Welcome back " + currentUser.getUsername());
		return model;
	}
	@RequestMapping("/login")
	public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("login");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}	
	@RequestMapping("/Userlogin")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		userService.validateUser(username, password, session);
		
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("user", session.getAttribute("user"));
		currentUser = (UserEntity) session.getAttribute("user");
		model.addObject("welcomeMessage", "Welcome back " + currentUser.getUsername());
		return model;
	}	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		session.setAttribute("user", null);
		this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("logout");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}	
	@RequestMapping("/signup")
	public ModelAndView signupPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("signUp");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}
	@RequestMapping("/UserSignUp")
	public ModelAndView signup(HttpServletRequest request,HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("sex") String sex) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		if(sex.equals("Male")) {
			sex = "m";
		} else {
			sex = "f";
		}
		
		userService.createUser(username, password, sex, session);
		
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("user", session.getAttribute("user"));
		currentUser = (UserEntity) session.getAttribute("user");
		model.addObject("welcomeMessage", "Welcome back " + currentUser.getUsername());
		return model;
	}	
	@RequestMapping("/foodSettings")
	public ModelAndView foodSettingsPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		scheduleItemService.getUserSchedule(currentUser.getUsername(), session);
		this.addDietaryReqs(session);
		
		ModelAndView model = new ModelAndView("foodSettings");
		model.addObject("user", session.getAttribute("user"));
		model.addObject("schedule", session.getAttribute("schedule"));
		if(session.getAttribute("isVegiterian") == "yes") {model.addObject("vegiterian", "yes");}
		if(session.getAttribute("isVegan") == "yes") {model.addObject("vegan", "yes");}
		if(session.getAttribute("isLactoseInt") == "yes") {model.addObject("lactoseInt", "yes");}
		if(session.getAttribute("isGlutenInt") == "yes") {model.addObject("glutenInt", "yes");}
		if(session.getAttribute("isNutAlerg") == "yes") {model.addObject("nutAlerg", "yes");}
		if(session.getAttribute("isKosher") == "yes") {model.addObject("kosher", "yes");}
		if(session.getAttribute("isHalal") == "yes") {model.addObject("halal", "yes");}
		return model;
	}
	@RequestMapping("/addMeal")
	public ModelAndView addMeal(HttpServletRequest request,HttpServletResponse response, @RequestParam("day") String day, @RequestParam("time") String time, @RequestParam("type") String type, @RequestParam("dur") String dur) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		int dayCode = 0;
		switch(day) {
			case "Monday": dayCode = 1; break;
			case "Tuesday": dayCode = 2; break;
			case "Wednesday": dayCode = 3; break;
			case "Thursday": dayCode = 4; break;
			case "Friday": dayCode = 5; break;
			case "Saturday": dayCode = 6; break;
			case "Sunday": dayCode = 7; break;
		}
		
		time += ":00";
		System.out.println(time);
		dur += ":00";
		System.out.println(dur);
		
		java.sql.Time NTime = Time.valueOf(time);
		java.sql.Time NDur = Time.valueOf(dur);
		
		scheduleItemService.createScheduleItem(currentUser.getUsername(), "food", type, dayCode, NTime, NDur);
		
		ModelAndView model = new ModelAndView("foodSettings");
		model.addObject("user", session.getAttribute("user"));
		model.addObject("schedule", session.getAttribute("schedule"));
		if(session.getAttribute("isVegiterian") == "yes") {model.addObject("vegiterian", "yes");}
		if(session.getAttribute("isVegan") == "yes") {model.addObject("vegan", "yes");}
		if(session.getAttribute("isLactoseInt") == "yes") {model.addObject("lactoseInt", "yes");}
		if(session.getAttribute("isGlutenInt") == "yes") {model.addObject("glutenInt", "yes");}
		if(session.getAttribute("isNutAlerg") == "yes") {model.addObject("nutAlerg", "yes");}
		if(session.getAttribute("isKosher") == "yes") {model.addObject("kosher", "yes");}
		if(session.getAttribute("isHalal") == "yes") {model.addObject("halal", "yes");}
		return model;
	}
	@RequestMapping("/setDietReqs")
	public ModelAndView setDietReqs(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "Vegetarian", required = false) String vegeterian, @RequestParam(value = "Vegan", required = false) String vegan, @RequestParam(value = "LacInt", required = false) String LacInt, @RequestParam(value = "GlucInt", required = false) String GlucInt, @RequestParam(value = "NutAlg", required = false) String NutAlg, @RequestParam(value = "Kosher", required = false) String Kosher, @RequestParam(value = "Halal", required = false) String Halal) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		String reqs = "";
		if(vegeterian != null){reqs += vegeterian+",";}
		if(vegan != null){reqs += vegan+",";}
		if(LacInt != null){reqs += LacInt+",";}
		if(GlucInt != null){reqs += GlucInt+",";}
		if(NutAlg != null){reqs += NutAlg+",";}
		if(Kosher != null){reqs += Kosher+",";}
		if(Halal != null){reqs += Halal;}
		
		System.out.println(reqs);		
		userService.setDietReqs(reqs, currentUser);
		
		ModelAndView model = new ModelAndView("foodSettings");
		model.addObject("user", session.getAttribute("user"));
		model.addObject("schedule", session.getAttribute("schedule"));
		this.addDietaryReqs(session);
		if(session.getAttribute("isVegiterian") == "yes") {model.addObject("vegiterian", "yes");}
		if(session.getAttribute("isVegan") == "yes") {model.addObject("vegan", "yes");}
		if(session.getAttribute("isLactoseInt") == "yes") {model.addObject("lactoseInt", "yes");}
		if(session.getAttribute("isGlutenInt") == "yes") {model.addObject("glutenInt", "yes");}
		if(session.getAttribute("isNutAlerg") == "yes") {model.addObject("nutAlerg", "yes");}
		if(session.getAttribute("isKosher") == "yes") {model.addObject("kosher", "yes");}
		if(session.getAttribute("isHalal") == "yes") {model.addObject("halal", "yes");}
		return model;
	}
	@RequestMapping("/recordMeal")
	public ModelAndView recordMealPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
			
		HttpSession session = this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("recordMeal");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}
	@RequestMapping("/submitMeal")
	public ModelAndView submitMeal(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "mealTitle", required = false) String mealTitle, @RequestParam(value = "mealDesc", required = false) String mealDesc, @RequestParam(value = "mealDate", required = false) String mealDate, @RequestParam(value = "mealTime", required = false) String mealTime, @RequestParam(value = "mealLoc", required = false) String location, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "mealWeight", required = false) Integer mealWeight, @RequestParam(value = "mealCal", required = false) Integer mealCal, @RequestParam(value = "f&v") Integer fv, @RequestParam(value = "carb") Integer carb, @RequestParam(value = "protein") Integer protein, @RequestParam(value = "dairy") Integer dairy, @RequestParam(value = "o&s") Integer os, @RequestParam(value = "jF") Integer junk) throws Exception {
			
		//TBD
		
		HttpSession session = this.setSessionInfo(request, response);
		
		String tempTime = mealDate + " " + mealTime + ":00";
		System.out.println(tempTime);
		
		java.sql.Timestamp dateTime = Timestamp.valueOf(tempTime);
		
		Integer eventId =  eventService.createEvent(currentUser.getUsername(), dateTime, "meal");
		
		mealService.createMeal(eventId, mealTitle, mealDesc, location, type, mealWeight, mealCal, fv, carb, protein, dairy, os, junk);
		
		ModelAndView model = new ModelAndView("mealHistory");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}
	@RequestMapping("/mealHistory")
	public ModelAndView mealHistory(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//TBD
		
		HttpSession session = this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("mealHistory");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}
	
	
	
	
	public HttpSession setSessionInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String action = request.getRequestURI();
		System.out.println("action is " + action);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null) {
			currentUser = new UserEntity();
			currentUser.setSex("m");
			session.setAttribute("user", currentUser);
		} else {
			currentUser = (UserEntity) session.getAttribute("user");
		}
		
		System.out.println("Gender Test = " + currentUser.getSex());
		System.out.println(currentUser.getUsername());
		return session;
	}
	
	public void addDietaryReqs(HttpSession session) {
		String reqs = currentUser.getDietRqs();
		
		session.setAttribute("isVegiterian", "no");
		session.setAttribute("isVegan", "no");	
		session.setAttribute("isLactoseInt", "no");
		session.setAttribute("isGlutenInt", "no");
		session.setAttribute("isNutAlerg", "no");
		session.setAttribute("isKosher", "no");
		session.setAttribute("isHalal", "no");
		
		String dietReqs[] = new String[7];
		
		dietReqs = reqs.split(",");	
		
		for(int i = 0; i < dietReqs.length; i++) {
			switch(dietReqs[i]) {
				case "V":
					session.setAttribute("isVegiterian", "yes");
					break;
				case "VV":
					session.setAttribute("isVegan", "yes");			
					break;
				case "L":
					session.setAttribute("isLactoseInt", "yes");
					break;
				case "G":
					session.setAttribute("isGlutenInt", "yes");
					break;
				case "N":
					session.setAttribute("isNutAlerg", "yes");
					break;
				case "K":
					session.setAttribute("isKosher", "yes");
					break;
				case "H":
					session.setAttribute("isHalal", "yes");
					break;
			}
		}
	}
	
}
