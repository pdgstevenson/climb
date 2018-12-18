package uk.co.tatari.climb.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.tatari.climb.repository.UserRepository;


@Controller
public class LoginController {

	private static final String VIEW_LOGIN_FORM = "login/login";

	@Autowired UserRepository sampleTrackerRepository;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String initLoginPage(Map<String, Object> model, Authentication authentication) {

		return VIEW_LOGIN_FORM;
	}
	
	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	public String initLoginPageAfterLogout(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

	    if (authentication != null){  
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	    }
		return "redirect:/login";
	}
}
