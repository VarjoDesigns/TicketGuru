package fi.rbmk.ticketguru.ticket;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.eventTicket.EventTicket;
import fi.rbmk.ticketguru.saleRow.SaleRow;
import fi.rbmk.ticketguru.ticketStatus.TicketStatus;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Tickets")
public class Ticket extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_ID")
    private Long ticket_ID;

    @ManyToOne
    @JoinColumn(name = "eventTicket_ID")
    private EventTicket eventTicket;

    @ManyToOne
    @JoinColumn(name = "ticketStatus_ID")
    private TicketStatus ticketStatus;

    @Length(max = 200)
    @Column(name = "checkSum", unique = true)
    private String checkSum;

    @ManyToOne
    @JoinColumn(name = "saleRow_ID")
    private SaleRow saleRow;

    @Column(name = "valid")
    private LocalDateTime valid = LocalDateTime.now();

    @Column(name = "invalid")
    private LocalDateTime invalid;

    public Ticket() {
    }

    public Ticket(Ticket ticket) {
    }

    public Ticket(EventTicket eventTicket, SaleRow saleRow) {
        SecureRandom random = new SecureRandom();
        this.eventTicket = eventTicket;
        this.checkSum = new BigInteger(128, random).toString(32);
        this.saleRow = saleRow;
    }

    // Getters
    public Long getTicket_ID() { return ticket_ID; }
    public EventTicket getEventTicket() { return eventTicket; }
    public TicketStatus getTicketStatus() { return ticketStatus; }
    public String getCheckSum() { return checkSum; }
    public SaleRow getSaleRow() { return saleRow; }
    public LocalDateTime getValid() { return valid; }
    public LocalDateTime getInvalid() { return invalid; }

    // Setters
    public void setEventTicket(EventTicket eventTicket) { this.eventTicket = eventTicket; }
    public void setTicketStatus(TicketStatus ticketStatus) { this.ticketStatus = ticketStatus; }
    public void setSaleRow(SaleRow saleRow) { this.saleRow = saleRow; }
    public void setCheckSum(String checkSum) { this.checkSum = checkSum; }
    public void setInvalid() { this.invalid = LocalDateTime.now(); }
}
