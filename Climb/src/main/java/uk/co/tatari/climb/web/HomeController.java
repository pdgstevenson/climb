package uk.co.tatari.climb.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController  extends BaseController{

	private static final String VIEW_HOME_PAGE = "home/home";

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String initHomePage(Map<String, Object> model) {
		return VIEW_HOME_PAGE;
	}
}
