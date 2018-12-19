package uk.co.tatari.climb.service;

import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class PasswordServiceImpl implements PasswordService{

	private static Logger LOGGER = LoggerFactory.getLogger(PasswordServiceImpl.class);
	public static String validPasswordMessage = "OK";
	/**
	 * validate a password based on the rules that length must be between 8 and 16 characters
	 * containing no white space and must have 3 out of 4 of at least 1 uppercase, 1 lowercase,
	 * 1 digit or 1 special character.
	 */
	@Override
	public String validatePassword(String password) {
		
		CharacterCharacteristicsRule rule3of4 = new CharacterCharacteristicsRule();
		rule3of4.setNumberOfCharacteristics(3);
		rule3of4.getRules().add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
		rule3of4.getRules().add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
		rule3of4.getRules().add(new CharacterRule(EnglishCharacterData.Digit, 1));
		rule3of4.getRules().add(new CharacterRule(EnglishCharacterData.Special, 1));
		PasswordValidator validator = new PasswordValidator(new LengthRule(8, 16), rule3of4, new WhitespaceRule());			
		RuleResult result = validator.validate(new PasswordData(new String(password)));
		if(!result.isValid()) {
			for(String s: validator.getMessages(result)) {
				LOGGER.debug(s);
			}			
		}
		return result.isValid() ? validPasswordMessage : String.join(" ", validator.getMessages(result));		
	}

}




