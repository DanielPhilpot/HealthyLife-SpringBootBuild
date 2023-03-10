package HealthyLife.SpringBootBuild.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import HealthyLife.SpringBootBuild.Model.User;
import HealthyLife.SpringBootBuild.service.UserService;
import HealthyLife.SpringBootBuild.Model.UserEntity;


@Controller
public class controller {
	
	private UserEntity currentUser; 
	
	@Autowired
	private UserService userService;
	
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
