package uk.co.tatari.climb.web.utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 2, max = 3)	
	private String initials;
	
	@NotNull
	//@Size(min = 8, max = 16)
	private String password1;
	
	@NotNull
	//@Size(min = 8, max = 16)
	private String password2;
	

	public RegisterModel() {
		super();
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	public String getPassword() {
		
		return new BCryptPasswordEncoder().encode(password1);
	}
}
