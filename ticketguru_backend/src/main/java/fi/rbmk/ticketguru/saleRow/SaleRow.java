package fi.rbmk.ticketguru.saleRow;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import fi.rbmk.ticketguru.ticket.Ticket;
import fi.rbmk.ticketguru.saleEvent.SaleEvent;

@Entity
@Table(name = "SaleRows")
public class SaleRow extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleRow_ID")
	private Long saleRow_ID;

	@Column(name = "discount")
	private Long discount; // Pitäisikö olla % vai alennus euroina vai...? Varmaan % olisi paras -Mika

	@NotEmpty(message = "Sale event is required")
	@ManyToOne
	@JoinColumn(name = "saleEvent_ID")
	private SaleEvent saleEvent;

	@OneToOne
	@JoinColumn(name = "ticket_ID")
	private Ticket ticket;

	public SaleRow() {
		super();
	}

	public SaleRow(Long id, SaleEvent saleEvent, Ticket ticket, Long discount) {
		super();
		this.saleRow_ID = id;
		this.saleEvent = saleEvent;
		this.ticket = ticket;
		this.discount = discount;
	}

	// Getterit
	public SaleEvent getSaleEvent() {
		return this.saleEvent;
	}

	public Long getSaleRow_ID() {
		return this.saleRow_ID;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public Long getDiscount() {
		return this.discount;
	}

	// Setterit
	public void setSaleEvent(SaleEvent saleEvent) {
		this.saleEvent = saleEvent;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

}
