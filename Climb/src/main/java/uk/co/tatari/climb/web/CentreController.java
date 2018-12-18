package uk.co.tatari.climb.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uk.co.tatari.climb.repository.CentreRepository;


@Controller
@RequestMapping("/centre")
@SessionAttributes({"centre"})
public class CentreController  {

	private static Logger LOGGER = LoggerFactory.getLogger(CentreController.class);
	private static final String VIEW_CENTRE_FORM = "centre/centreForm";	


	@Autowired CentreRepository centreRepository;


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewCentreForm(Map<String, Object> model) {
    		LOGGER.debug("In showNewCentreForm...");
        return VIEW_CENTRE_FORM;
    }

}
