package fi.rbmk.ticketguru.ticket;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.eventTicket.EventTicket;
import fi.rbmk.ticketguru.saleRow.SaleRow;
import fi.rbmk.ticketguru.ticketStatus.TicketStatus;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "tickets")
public class Ticket extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticket_id;

    @NotNull(message = "EventTicket is required")
    @ManyToOne
    @JoinColumn(name = "event_ticket_id")
    private EventTicket eventTicket;

    @NotNull(message = "TicketStatus is required")
    @ManyToOne
    @JoinColumn(name = "ticket_status_id")
    private TicketStatus ticketStatus;

    @Length(max = 100)
    @Column(name = "checksum", unique = true)
    private String checksum;

    @ManyToOne
    @JoinColumn(name = "sale_row_id")
    private SaleRow saleRow;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "invalid")
    private LocalDateTime invalid;

    public Ticket() {
    }

    public Ticket(Ticket ticket) {
    }

    public Ticket(EventTicket eventTicket, SaleRow saleRow) {
        SecureRandom random = new SecureRandom();
        this.eventTicket = eventTicket;
        this.checksum = new BigInteger(128, random).toString(32);
        this.saleRow = saleRow;
    }

    // Getters
    public Long getTicket_id() { return ticket_id; }
    public EventTicket getEventTicket() { return eventTicket; }
    public TicketStatus getTicketStatus() { return ticketStatus; }
    public String getChecksum() { return checksum; }
    public SaleRow getSaleRow() { return saleRow; }
    public LocalDateTime getCreated() { return created; }
    public LocalDateTime getInvalid() { return invalid; }

    // Setters
    public void setEventTicket(EventTicket eventTicket) { this.eventTicket = eventTicket; }
    public void setTicketStatus(TicketStatus ticketStatus) { this.ticketStatus = ticketStatus; }
    public void setSaleRow(SaleRow saleRow) { this.saleRow = saleRow; }
    public void setChecksum(String checksum) { this.checksum = checksum; }
    public void setInvalid() { this.invalid = LocalDateTime.now(); }
}
