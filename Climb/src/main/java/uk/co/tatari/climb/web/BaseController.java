package uk.co.tatari.climb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import uk.co.tatari.climb.domain.Centre;
import uk.co.tatari.climb.repository.CentreRepository;
import uk.co.tatari.climb.repository.RoomRepository;
import uk.co.tatari.climb.repository.WallRepository;


@Controller
public class BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	@Autowired CentreRepository centreRepository;
	@Autowired RoomRepository roomRepository;
	@Autowired WallRepository wallRepository;

	public final Map<String, Centre> centreMap = new HashMap<String, Centre>(0);
	@ModelAttribute("centres")
	public List<Centre> getCentres() {
		LOGGER.debug("getCentres calling find all");	
		List<Centre> centres = (List<Centre>) centreRepository.findAll();
		for(Centre centre: centres) {
			centreMap.put(centre.getName(), centre);
		}
		return centres;
	}

}
