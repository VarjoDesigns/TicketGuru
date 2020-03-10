package fi.rbmk.ticketguru.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import fi.rbmk.ticketguru.eventTicket.EventTicket;
import fi.rbmk.ticketguru.saleRow.SaleRow;
import fi.rbmk.ticketguru.ticketStatus.TicketStatus;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    @NotEmpty(message = "Ticket checksum is required")
    @Length(max = 200)
    @Column(name = "checkSum")
    private String checkSum;

    @OneToOne(mappedBy = "ticket")
    private SaleRow saleRow;

    //private LocalDateTime timestamp;

    public Ticket() {
    }

    public Ticket(Ticket ticket) {
    }

    public Ticket(EventTicket eventTicket, TicketStatus ticketStatus, String checkSum) {
        this.eventTicket = eventTicket;
        this.ticketStatus = ticketStatus;
        this.checkSum = checkSum;

    }

    // Getters
    public Long getTicket_ID() {
        return this.ticket_ID;
    }

    public EventTicket getEventTicket() {
        return this.eventTicket;
    }

    public TicketStatus getTicketStatus() {
        return this.ticketStatus;
    }

    public String getCheckSum() {
        return this.checkSum;
    }

    public SaleRow getSaleRow() {
        return this.saleRow;
    }

    // Setters
    public void setEventTicket(EventTicket eventTicket) {
        this.eventTicket = eventTicket;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
}
