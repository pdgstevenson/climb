package uk.co.tatari.climb.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uk.co.tatari.climb.domain.Centre;
import uk.co.tatari.climb.domain.Room;
import uk.co.tatari.climb.domain.Wall;
import uk.co.tatari.climb.web.utils.SimpleLookupsMapEditor;


@Controller
@RequestMapping("/wall")
@SessionAttributes({"wall"})
public class WallController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(WallController.class);
	private static final String VIEW_WALL_FORM = "wall/wallForm";
	private static final String VIEW_WALL_DETAILS = "wall/wallDetails";

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Centre.class, new SimpleLookupsMapEditor<Centre>(centreMap));
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showWall(Map<String, Object> model, @PathVariable("id") Integer id) {
    		LOGGER.debug("In showWall");
    		Wall wall = wallRepository.findByWallId(id);
        model.put("wall", wall);   
        return VIEW_WALL_DETAILS;
    }
 
    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showWallForm(Map<String, Object> model, @PathVariable("id") Integer id) {
    		LOGGER.debug("In showWallForm");
    		Room room = roomRepository.findByRoomId(id);
    		Wall wall = new Wall(room);
        model.put("wall", wall);  
        return VIEW_WALL_FORM;
    }
    
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String processNewWallForm(@Valid Wall wall, BindingResult result, Map<String, Object> model) {
    		LOGGER.debug("In processNewWallForm...");
    		if (result.hasErrors()) {

    			return VIEW_WALL_FORM;
    		} 
    		wallRepository.save(wall);		
    		return "redirect:/room/" + wall.getRoom().getRoomId();
    }

}
