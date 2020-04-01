package fi.rbmk.ticketguru.eventTicket;

import fi.rbmk.ticketguru.event.Event;
import fi.rbmk.ticketguru.ticketType.TicketType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "EventTickets")
public class EventTicket extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventTicket_ID")
    private Long id;
    private Long ticketCount;
    private Double price;

    @NotNull(message = "Event must be set")
    @ManyToOne
    @JoinColumn(name = "event_ID")
    private Event event;

    @NotNull(message = "Ticket type must be set")
    @ManyToOne
    @JoinColumn(name = "ticketType_ID")
    private TicketType ticketType;

    public EventTicket() {
        super();
    }

    public EventTicket(EventTicket eventTicket) {
    }

    // Getters
    public Long getEventTicket_ID() {
        return id;
    }

    public Long getTicketCount() {
        return ticketCount;
    }

    public Double getPrice() {
        return price;
    }

    public Event getEvent() {
        return event;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    // Setters
    public void setEventTicket_ID(Long id) {
        this.id = id;
    }

    public void setTicketCount(Long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}