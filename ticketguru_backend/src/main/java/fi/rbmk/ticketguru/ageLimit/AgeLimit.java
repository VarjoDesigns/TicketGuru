package fi.rbmk.ticketguru.ageLimit;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.event.Event;

@Entity
@Table(name = "age_limits")
public class AgeLimit extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "age_limit_id")
	private Long ageLimit_id;

	@NotEmpty(message = "Age limit name must be set")
	@Length(max = 50)
	@Column(name = "name")
	private String name;

	@Length(max = 500)
	@Column(name = "info")
	private String info;
	
	@CreationTimestamp
	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@OneToMany(mappedBy = "ageLimit")
	private List<Event> events;

	public AgeLimit() {
	}

	public AgeLimit(AgeLimit ageLimit) {
	}

	public AgeLimit(String name) {
		this.name = name;
	}

	public AgeLimit(String name, String specifier) {
		this.name = name;
		this.info = specifier;
	}

	// Getters
	public Long getAgeLimit_id() { return this.ageLimit_id; }
	public String getName() { return this.name; }
	public String getInfo() { return this.info; }
	public LocalDateTime getCreated() { return created; }
	public LocalDateTime getInvalid() { return invalid; }
	public List<Event> getEvents() { return events; }

	// Setters
	public void setName(String name) { this.name = name; }
	public void setInfo(String info) { this.info = info; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }
	public void setEvents(List<Event> events) { this.events = events; }

}