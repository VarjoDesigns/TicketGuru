package fi.rbmk.ticketguru.ticketType;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.eventTicket.EventTicket;

@Entity
@Table(name = "ticket_types")
public class TicketType extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_type_id")
	private Long ticketType_id;

	@NotEmpty(message = "Ticket type name is required")
	@Length(max = 100)
	@Column(name = "name")
	private String name;
	
	@CreationTimestamp
	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@JsonIgnore
	@OneToMany(mappedBy = "ticketType")
	private List<EventTicket> eventTickets;

	public TicketType() {
	}

	public TicketType(TicketType ticketType) {
	}

	public TicketType(String name) {
		this.name = name;
	}

	// Getters
	public Long getTicketType_id() { return ticketType_id; }
	public String getName() { return name; }
	public LocalDateTime getCreated() { return created; }
	public LocalDateTime getInvalid() { return invalid; }
	public List<EventTicket> getEventTickets() { return eventTickets; }

	// Setters
	public void setName(String name) { this.name = name; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }

}
