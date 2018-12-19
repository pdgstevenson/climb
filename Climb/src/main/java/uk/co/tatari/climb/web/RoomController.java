package uk.co.tatari.climb.web;

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
import uk.co.tatari.climb.web.utils.SimpleLookupsMapEditor;


@Controller
@RequestMapping("/room")
@SessionAttributes({"room"})
public class RoomController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(RoomController.class);
	private static final String VIEW_ROOM_FORM = "room/roomForm";
	private static final String VIEW_ROOM_DETAILS = "room/roomDetails";

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Centre.class, new SimpleLookupsMapEditor<Centre>(centreMap));
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showRoom(Map<String, Object> model, @PathVariable("id") Integer id) {
    		LOGGER.debug("In showRoom");
    		Room room = roomRepository.findByRoomId(id);
        model.put("room", room);   
        return VIEW_ROOM_DETAILS;
    }
 
    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showRoomForm(Map<String, Object> model, @PathVariable("id") Integer id) {
    		LOGGER.debug("In showRoomForm");
    		Room room = new Room(centreRepository.findByCentreId(id));
        model.put("room", room);   
        return VIEW_ROOM_FORM;
    }
    
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String processNewRoomForm(@Valid Room room, BindingResult result, Map<String, Object> model) {
    		LOGGER.debug("In processNewRoomForm...");
    		if (result.hasErrors()) {

    			return VIEW_ROOM_FORM;
    		} 
    		roomRepository.save(room);		
    		return "redirect:/centre/" + room.getCentre().getCentreId();
    }

}
