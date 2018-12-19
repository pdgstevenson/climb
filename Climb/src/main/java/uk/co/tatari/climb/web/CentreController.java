package uk.co.tatari.climb.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import uk.co.tatari.climb.domain.Centre;
import uk.co.tatari.climb.domain.Room;
import uk.co.tatari.climb.repository.CentreRepository;
import uk.co.tatari.climb.web.utils.RegisterModel;


@Controller
@RequestMapping("/centre")
@SessionAttributes({"centre", "room"})
public class CentreController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(CentreController.class);
	private static final String VIEW_ROOM_FORM = "room/centreForm";
	private static final String VIEW_CENTRE_FORM = "centre/centreForm";
	private static final String VIEW_CENTRE_DETAILS = "centre/centreDetails";

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showVariant(Map<String, Object> model, @PathVariable("id") Integer id) {
    		LOGGER.debug("In showVariant");
    		Centre centre = centreRepository.findByCentreId(id);
        model.put("centre", centre);   
        return VIEW_CENTRE_DETAILS;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewCentreForm(Map<String, Object> model) {
    		LOGGER.debug("In showNewCentreForm...");
    		model.put("centre", new Centre()); 		
        return VIEW_CENTRE_FORM;
    }
    
    @RequestMapping(value = "/{id}/room/add", method = RequestMethod.GET)
    public String showRoomForm(Map<String, Object> model, @PathVariable("id") Integer id) {
    		LOGGER.debug("In showRoomForm");
    		Room room = new Room(centreRepository.findByCentreId(id));
        model.put("room", room);   
        return VIEW_ROOM_FORM;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processNewCentreForm(@Valid Centre centre, BindingResult result, Map<String, Object> model) {
    		LOGGER.debug("In processNewCentreForm...");
    		if (result.hasErrors()) {

    			return VIEW_CENTRE_FORM;
    		} 
    		centreRepository.save(centre);		
    		return "redirect:/centre/" + centre.getCentreId();
    }

}
