package uk.co.tatari.climb.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;



/**
 * Java domain object representing a row in the staff table of the samvar database.
 * @author paulgrimwood
 *
 */
@Entity
@Table(name = "climb_user")
public class UserEntity implements UserDetails  {

	private static Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);
	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Id
	@Column(name = "climb_user_id", columnDefinition = "int")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="climb_user_climb_user_id_seq")
	@SequenceGenerator(name="climb_user_climb_user_id_seq", sequenceName="climb_user_climb_user_id_seq", allocationSize=1)
	private Integer climbUserId;
	
	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "initials", columnDefinition = "text")
	private String username;	
	
	@NotNull
	@Size(min = 60, max = 60)
	@Column(name = "password", columnDefinition = "text")
	private String password;

    
	@NotNull
	@Column(name = "enabled", columnDefinition = "boolean")
	private Boolean enabled;
	

	    
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private Set<AuthorityEntity> authorities = new HashSet<AuthorityEntity>(0);

	public UserEntity() {
		super();
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<AuthorityEntity> getAuthorities() {
		
		return authorities;
	}

	public void setAuthorities(Set<AuthorityEntity> authorities) {		
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	

	
	public AuthorityEntity getAuthorityEntity(String rolename) {	
		
		for(AuthorityEntity authorityEntity: authorities) {
			
			if(authorityEntity.getRoleName().equals(rolename)) {
				return authorityEntity;
			}
		}
		return null;
	}
}
