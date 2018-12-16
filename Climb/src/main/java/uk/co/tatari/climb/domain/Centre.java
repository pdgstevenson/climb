package uk.co.tatari.climb.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "centre")
public class Centre implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "centre_id", columnDefinition = "integer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="centre_centre_id_seq")
	@SequenceGenerator(name="centre_centre_id_seq", sequenceName="centre_centre_id_seq", allocationSize=1)
	private Integer centreId;

	public Centre() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centreId == null) ? 0 : centreId.hashCode());
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
		Centre other = (Centre) obj;
		if (centreId == null) {
			if (other.centreId != null)
				return false;
		} else if (!centreId.equals(other.centreId))
			return false;
		return true;
	}

}
