package uk.co.tatari.climb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uk.co.tatari.climb.repository.UserRepository;


@Service
public class ClimbUserDetailsService implements UserDetailsService {

	private static Logger LOGGER = LoggerFactory.getLogger(ClimbUserDetailsService.class);
	@Autowired
	UserRepository userRepo;
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
		LOGGER.debug("In ClimbUserDetailsService.loadUserByUsername with username {}", username);
		return userRepo.findByUsername(username);
	}
}