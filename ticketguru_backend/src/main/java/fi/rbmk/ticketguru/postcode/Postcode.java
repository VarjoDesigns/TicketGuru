package fi.rbmk.ticketguru.postcode;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.venue.Venue;

import fi.rbmk.ticketguru.eventOrganizer.EventOrganizer;

@Entity
@Table(name = "postcodes")
public class Postcode extends ResourceSupport {

	@Id
	@NotEmpty(message = "Postcode")
	@Length(max = 10)
	@Column(name = "postcode_id")
	private String postcode_id;


	@NotEmpty(message = "City name is required")
	@Length(max = 100)
	@Column(name = "city")
	private String city;

	@NotEmpty(message = "Country name is required")
	@Length(max = 100)
	@Column(name = "country")
	private String country;

	@CreationTimestamp
	@Column(name = "created")
	private LocalDateTime created;

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

	public Postcode(String postcode_ID, String city, String country) {
		super();
		this.postcode_id = postcode_ID;
		this.city = city;
		this.country = country;
	}

	// Getterit
	public String getPostcode_id() { return postcode_id; }
	public String getCity() { return city; }
	public String getCountry() { return country; }
	public LocalDateTime getCreated() { return created; }
	public LocalDateTime getInvalid() {return invalid; }
	public List<Venue> getVenues() { return venues; }
	public List<EventOrganizer> getEventOrganizers() { return eventOrganizers; }

	// Setterit
	public void setPostcode_id(String postcode_id) { this.postcode_id = postcode_id; }
	public void setCity(String city) { this.city = city; }
	public void setCountry(String country) { this.country = country; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }

	@Override
	public String toString() {
		return "PostCodes [postcode_ID=" + postcode_id + ", city=" + city + ", country="
				+ country + "]";
	}

}
