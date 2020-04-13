package fi.rbmk.ticketguru.eventType;

import fi.rbmk.ticketguru.event.Event;

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
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

@Entity
@Table(name = "EventTypes")
public class EventType extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventType_ID")
	private Long eventType_ID;

	@NotEmpty(message = "Event type name is required")
	@Length(max = 100)
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Event info is required")
	@Length(max = 500)
	@Column(name = "info")
	private String info;

	@Column(name = "valid")
	private LocalDateTime valid = LocalDateTime.now();

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventType")
	private List<Event> events;

	public EventType() {
	}

	public EventType(EventType eventType) {
	}

	public EventType(String name, String info) {
		super();
		this.name = name;
		this.info = info;
	}

	// Getters
	public Long getEventType_ID() { return eventType_ID; }
	public String getName() { return name; }
	public String getInfo() { return info; }
	public LocalDateTime getValid() { return valid; }
	public LocalDateTime getInvalid() { return invalid; }
	public List<Event> getEvents() { return events; }

	// Setters
	public void setName(String name) { this.name = name; }
	public void setInfo(String info) { this.info = info; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }

	@Override
	public String toString() {
		return "EventType[id=" + eventType_ID + ", name=" + name + ", info=" + info + "]";
	}
}