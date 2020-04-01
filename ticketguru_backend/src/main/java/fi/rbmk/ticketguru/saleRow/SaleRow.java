package fi.rbmk.ticketguru.saleRow;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@NotNull(message = "Sale event is required")
	@ManyToOne
	@JoinColumn(name = "saleEvent_ID")
	private SaleEvent saleEvent;

	@OneToMany(mappedBy = "saleRow")
	private List<Ticket> tickets;

	public SaleRow() {
		super();
	}

	public SaleRow(Long id, SaleEvent saleEvent, Long discount) {
		super();
		this.saleRow_ID = id;
		this.saleEvent = saleEvent;
		this.discount = discount;
	}

	// Getterit
	public SaleEvent getSaleEvent() {
		return this.saleEvent;
	}

	public Long getSaleRow_ID() {
		return this.saleRow_ID;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public Long getDiscount() {
		return this.discount;
	}

	// Setterit
	public void setSaleEvent(SaleEvent saleEvent) {
		this.saleEvent = saleEvent;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

}
