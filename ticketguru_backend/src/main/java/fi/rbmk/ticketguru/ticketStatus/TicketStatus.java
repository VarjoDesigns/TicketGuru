package fi.rbmk.ticketguru.ticketStatus;

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

import fi.rbmk.ticketguru.ticket.Ticket;

@Entity
@Table(name = "TicketStatuses")
public class TicketStatus extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticketStatus_ID")
	private Long ticketStatus_ID;

	@NotEmpty(message = "Ticket status is required")
	@Length(max = 50)
	@Column(name = "name")
	private String name;
	
	@Column(name = "valid")
	private LocalDateTime valid = LocalDateTime.now();

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@OneToMany(mappedBy = "ticketStatus")
	private List<Ticket> tickets;

	public TicketStatus() {
	}

	public TicketStatus(TicketStatus ticketStatus) {
	}

	public TicketStatus(String name) {
		this.name = name;
	}

	// Getters
	public Long getTicketStatus_ID() { return this.ticketStatus_ID; }
	public String getName() { return this.name; }
	public LocalDateTime getValid() { return valid; }
	public LocalDateTime getInvalid() { return invalid; }
	public List<Ticket> getTickets() { return tickets; }

	// Setters
	public void setName(String name) { this.name = name; }
	public void setInvalid() {  this.invalid = LocalDateTime.now(); }

}