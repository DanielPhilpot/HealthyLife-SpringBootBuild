package HealthyLife.SpringBootBuild.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import HealthyLife.SpringBootBuild.Model.User;


@Controller
public class controller {
	
	private User currentUser; 
	
	
	@RequestMapping("/help")
	public ModelAndView welcomePage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//this.setSessionInfo(session);
		System.out.println("Test A");
		
		String action = request.getRequestURI();
		System.out.println("action is " + action);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null) {
			currentUser = new User();
			session.setAttribute("user", currentUser);
		} else {
			currentUser = (User) session.getAttribute("user");
		}
		System.out.println("Test B");
		
		currentUser.setGender();
		System.out.println("Gender Test = " + currentUser.getSex());
		
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("user", session.getAttribute("user"));
		return model;
	}
	
	//remember to add user to session scope 
	
	
	public void setSessionInfo(HttpSession session) throws Exception {
		
		//String action = request.getRequestURI();
		//System.out.println("action is " + action);
		
		//HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null) {
			currentUser = new User();
			session.setAttribute("user", currentUser);
		} else {
			currentUser = (User) session.getAttribute("user");
		}
	}
}
