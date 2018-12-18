package uk.co.tatari.climb.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="st_authority")
public class AuthorityEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	


	@Id
	@Column(name = "st_authority_id", columnDefinition = "bigint")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="st_authority_st_authority_id_seq")
	@SequenceGenerator(name="st_authority_st_authority_id_seq", sequenceName="st_authority_st_authority_id_seq", allocationSize=1)
	private long id;

	/**
	 * Entries in database are in format ROLE_ADMIN, ROLE_USER
	 * In SecurityConfig.configure these are expressed as ADMIN, USER respectively.
	 * Spring security strips the 'ROLE_' substring
	 */
	@Column(name = "role_name", columnDefinition = "text")
	private String roleName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	
	public AuthorityEntity() {
		super();

	}

	public AuthorityEntity(String roleName, UserEntity user) {
		this.roleName = roleName;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {

		return roleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityEntity other = (AuthorityEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

}