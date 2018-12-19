package uk.co.tatari.climb.web;

import java.util.Map;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


import uk.co.tatari.climb.domain.UserEntity;
import uk.co.tatari.climb.repository.UserRepository;
import uk.co.tatari.climb.service.PasswordService;
import uk.co.tatari.climb.service.PasswordServiceImpl;
import uk.co.tatari.climb.web.utils.RegisterModel;

@Controller
//@RequestMapping("/register")
@SessionAttributes({"registerModel", "msg"})
public class RegisterController  extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
    private static final String VIEW_REGISTER_FORM = "admin/register";
	@Autowired UserRepository userRepository;
	@Autowired PasswordService passwordService;
 
	/**
	 * Presents blank register form to the user.
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Map<String, Object> model) {
   	
   		model.replace("msg", "");
    		model.replace("errmsg", "");
        model.put("registerModel", new RegisterModel());       
        return VIEW_REGISTER_FORM;
    }
    
    /**
     * Used to process the register form.
     * Currently creates a user with one entry in the ST_AUTHORITY table with role ROLE_USER
     * @param registerModel
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegisterForm(@Valid RegisterModel registerModel, BindingResult result, Map<String, Object> model) {
   	
		if (result.hasErrors()) {

			return VIEW_REGISTER_FORM;
		} 
		String passwordValidationMessage = passwordService.validatePassword(registerModel.getPassword1());
		LOGGER.debug(passwordValidationMessage);
		if(!passwordValidationMessage.equals(PasswordServiceImpl.validPasswordMessage)) {
			model.put("errmsg", passwordValidationMessage);
			return VIEW_REGISTER_FORM;
		}
		if (!registerModel.getPassword1().equals(registerModel.getPassword2())) {

			result.rejectValue("password2", "password.notmatched");
			return VIEW_REGISTER_FORM;
		} 
		try {
			UserEntity user = new UserEntity(registerModel.getInitials(), 
					registerModel.getPassword(), 
					Stream.of("ROLE_USER").toArray(String[]::new));
			userRepository.save(user);
		} catch (Exception e) {
			
			e.printStackTrace();
			model.put("errmsg", "Your request cannot been sent. Please contact the climbing centre team.");
			return VIEW_REGISTER_FORM;
		}
        model.put("msg", "You are now in the system.");       
        return VIEW_REGISTER_FORM;
    }

}
