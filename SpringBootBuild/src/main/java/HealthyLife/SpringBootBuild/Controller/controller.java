package HealthyLife.SpringBootBuild.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import HealthyLife.SpringBootBuild.service.ScheduleItemService;
//import HealthyLife.SpringBootBuild.Model.User;
import HealthyLife.SpringBootBuild.service.UserService;
import HealthyLife.SpringBootBuild.Model.UserEntity;


@Controller
public class controller {
	
	private UserEntity currentUser; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScheduleItemService scheduleItemService;
	
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
		
		ModelAndView model = new ModelAndView("foodSettings");
		model.addObject("user", session.getAttribute("user"));
		model.addObject("schedule", session.getAttribute("schedule"));
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
		return model;
	}
	@RequestMapping("/recordMeal")
	public ModelAndView recordMealPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = this.setSessionInfo(request, response);
		
		ModelAndView model = new ModelAndView("recordMeal");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}
	@RequestMapping("/mealHistory")
	public ModelAndView mealHistory(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
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
	
}
