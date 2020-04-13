package fi.rbmk.ticketguru.postcode;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.venue.Venue;

import fi.rbmk.ticketguru.eventOrganizer.EventOrganizer;

@Entity
@Table(name = "Postcodes")
public class Postcode extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "postcode_ID")
	private Long postcode_ID;

	@NotEmpty(message = "Postcode")
	@Length(max = 10)
	@Column(name = "postcode")
	private String postcode;


	@NotEmpty(message = "City name is required")
	@Length(max = 250)
	@Column(name = "city")
	private String city;

	@NotEmpty(message = "Country name is required")
	@Length(max = 250)
	@Column(name = "country")
	private String country;

	@Column(name = "valid")
	private LocalDateTime valid = LocalDateTime.now();

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@OneToMany(mappedBy = "postcode")
	private List<Venue> venues;

	@OneToMany(mappedBy = "postcode")
	private List<EventOrganizer> eventOrganizers;

	public Postcode() {
		super();
	}

	public Postcode(Postcode postcode) {
	}

	public Postcode(String postcode, String city, String country) {
		super();
		this.postcode = postcode;
		this.city = city;
		this.country = country;
	}

	// Getterit
	public Long getPostcode_ID() { return postcode_ID; }
	public String getPostcode() { return postcode; }
	public String getCity() { return city; }
	public String getCountry() { return country; }
	public LocalDateTime getValid() { return valid; }
	public LocalDateTime getInvalid() {return invalid; }
	public List<Venue> getVenues() { return venues; }
	public List<EventOrganizer> getEventOrganizers() { return eventOrganizers; }

	// Setterit
	public void setPostcode(String postcode) { this.postcode = postcode; }
	public void setCity(String city) { this.city = city; }
	public void setCountry(String country) { this.country = country; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }

	@Override
	public String toString() {
		return "PostCodes [postcode_ID=" + postcode + ", postcode=" + postcode + ", city=" + city + ", country="
				+ country + "]";
	}

}
