package fi.rbmk.ticketguru.eventOrganizer;

import fi.rbmk.ticketguru.event.Event;
import fi.rbmk.ticketguru.postcode.Postcode;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "event_organizers")
public class EventOrganizer extends ResourceSupport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_organizer_id")
	private Long eventOrganizer_id;

	@NotEmpty(message = "Event organizer name is required")
	@Length(max = 100)
	@Column(name = "name")
	private String name;
	
	@Length(max = 150)
	@Column(name = "street_address")
	private String streetAddress;
	
	@Length(max = 25)
	@Column(name = "tel")
	private String tel;

	@Length(max = 150)
	@Column(name = "email")
	private String email;

	@Length(max = 250)
	@Column(name = "www")
	private String www;

	@Length(max = 250)
	@Column(name = "contact_person")
	private String contactPerson;

	@NotNull(message = "Postcode is required")
	@ManyToOne
	@JoinColumn(name = "postcode_id")
	private Postcode postcode;

	@CreationTimestamp
	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventOrganizer")
	private List<Event> events;

	public EventOrganizer() {
	}

	public EventOrganizer(EventOrganizer eventOrganizer) {
	}

	public EventOrganizer(Long id, Postcode postcode, String name, String streetAddress, String tel, String email,
			String www, String contactPerson) {
		super();
		this.eventOrganizer_id = id;
		this.postcode = postcode;
		this.name = name;
		this.streetAddress = streetAddress;
		this.tel = tel;
		this.email = email;
		this.www = www;
		this.contactPerson = contactPerson;
	}

	// Getters
	public Long getEventOrganizer_id() { return eventOrganizer_id; }
	public String getName() { return name; }
	public String getStreetAddress() { return streetAddress; }
	public String getTel() { return tel; }
	public String getEmail() { return email; }
	public String getWWW() { return www; }
	public String getContactPerson() { return contactPerson; }
	public Postcode getPostcode() { return postcode; }
	public LocalDateTime getCreated() { return created; }
	public LocalDateTime getInvalid() { return invalid; }
	public List<Event> getEvents() { return events; }

	// Setters
	public void setName(String name) { this.name = name; }
	public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
	public void setTel(String tel) { this.tel = tel; }
	public void setEmail(String email) { this.email = email; }
	public void setWWW(String www) { this.www = www; }
	public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
	public void setPostcode(Postcode postcode) { this.postcode = postcode; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }

	@Override
	public String toString() {
		return "EventOrganizer[id=" + eventOrganizer_id + ", name=" + name + ", streetAddress=" + streetAddress
				+ ", tel=" + tel + ", email=" + email + ", www=" + www + ", contactPerson=" + contactPerson
				+ ", postcode=" + postcode + "]";
	}
}