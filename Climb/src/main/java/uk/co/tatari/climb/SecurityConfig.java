package uk.co.tatari.climb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import uk.co.tatari.climb.service.ClimbUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger LOGGER = LoggerFactory.getLogger(ClimbUserDetailsService.class);
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider(ClimbUserDetailsService stUserDetailsService) {
		LOGGER.debug("In SecurityConfig.daoAuthenticationProvider");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(stUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	@Autowired
	public void configAuthenticationProvider(AuthenticationManagerBuilder auth,
			DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
		LOGGER.debug("In SecurityConfig.configAuthenticationProvider");
		auth.authenticationProvider(daoAuthenticationProvider);
	}
	
	

	private CsrfTokenRepository csrfTokenRepository() 
	{ 
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
		repository.setSessionAttributeName("_csrf");
		return repository; 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
/*
		//requires secure will bounce any attempt to access via http to https		
		http.requiresChannel().antMatchers("/login*").requiresSecure();
		http.requiresChannel().antMatchers("/register*").requiresSecure();
		http.requiresChannel().antMatchers("/batch*").requiresSecure();
		http.requiresChannel().antMatchers("/sample*").requiresSecure();
		http.requiresChannel().antMatchers("/variant*").requiresSecure();
		http.requiresChannel().antMatchers("/home*").requiresSecure();
		http.requiresChannel().antMatchers("/database*").requiresSecure();
		http.requiresChannel().antMatchers("/gene*").requiresSecure();
		http.requiresChannel().antMatchers("/variant*").requiresSecure();
		http.requiresChannel().antMatchers("/log*").requiresSecure();
		http.requiresChannel().antMatchers("/disorder*").requiresSecure();
		http.requiresChannel().antMatchers("/coverage*").requiresSecure();
		http.requiresChannel().antMatchers("/primer*").requiresSecure();
		http.requiresChannel().antMatchers("/genbank*").requiresSecure();		
		http.requiresChannel().antMatchers("/confirmations*").requiresSecure();
		http.requiresChannel().antMatchers("/staff*").requiresSecure();
		http.requiresChannel().antMatchers("/reclassifications*").requiresSecure();
*/
		
		http.authorizeRequests().antMatchers("/login/**").permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/home");
		http.authorizeRequests().antMatchers("/centre/**").hasRole("USER").and().formLogin().and().logout().logoutSuccessUrl("/login/logout");
		http.authorizeRequests().antMatchers("/home").hasRole("USER").and().formLogin();
		http.csrf()
		.csrfTokenRepository(csrfTokenRepository());

	}
}
