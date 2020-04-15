package fi.rbmk.ticketguru.saleRow;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import fi.rbmk.ticketguru.ticket.Ticket;
import fi.rbmk.ticketguru.saleEvent.SaleEvent;

@Entity
@Table(name = "sale_rows")
public class SaleRow extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_row_id")
	private Long saleRow_id;

	@Column(name = "discount")
	private Long discount; // Pitäisikö olla % vai alennus euroina vai...? Varmaan % olisi paras -Mika

	@CreationTimestamp
	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "invalid")
	private LocalDateTime invalid;

	@NotNull(message = "Sale event is required")
	@ManyToOne
	@JoinColumn(name = "sale_event_id")
	private SaleEvent saleEvent;

	@OneToMany(mappedBy = "saleRow")
	private List<Ticket> tickets;

	public SaleRow() {
		super();
	}

	public SaleRow(SaleEvent saleEvent) {
		super();
		this.saleEvent = saleEvent;
	}

	public SaleRow(SaleEvent saleEvent, Long discount) {
		super();
		this.saleEvent = saleEvent;
		this.discount = discount;
	}

	// Getterit
	public Long getSaleRow_id() { return saleRow_id; }
	public Long getDiscount() { return discount; }
	public LocalDateTime getCreated() { return created; }
	public LocalDateTime getInvalid() { return invalid; }
	public SaleEvent getSaleEvent() { return saleEvent; }
	public List<Ticket> getTickets() { return tickets; }

	// Setterit
	public void setSaleEvent(SaleEvent saleEvent) { this.saleEvent = saleEvent; }
	public void setDiscount(Long discount) { this.discount = discount; }
	public void setInvalid() { this.invalid = LocalDateTime.now(); }

}
