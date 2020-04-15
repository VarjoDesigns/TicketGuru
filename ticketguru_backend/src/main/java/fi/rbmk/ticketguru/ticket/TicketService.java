package fi.rbmk.ticketguru.ticket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import fi.rbmk.ticketguru.eventTicket.EventTicket;
import fi.rbmk.ticketguru.saleRow.SaleRow;
import fi.rbmk.ticketguru.ticketStatus.TicketStatus;
import fi.rbmk.ticketguru.ticketStatus.TicketStatusRepository;

@Service
public class TicketService {

    @Autowired
    TicketRepository tRepository;
    @Autowired
    TicketStatusRepository tStatusRepository;

    public List<Ticket> generateTickets(SaleRow saleRow, EventTicket eventTicket, Long count) {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        TicketStatus ticketStatus = tStatusRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1"));
        for (int i = 0; i < count; i++) {
            Ticket ticket = new Ticket(eventTicket, saleRow);
            ticket.setTicketStatus(ticketStatus);
            ticketList.add(tRepository.save(ticket));
        }
        return ticketList;
    }

    public List<Object> validate(String checksum) {
        List<Object> result = new ArrayList<Object>();
        Ticket ticket = null;
        ticket = tRepository.findByChecksum(checksum);
        if (ticket == null) {
            throw new ResourceNotFoundException("Invalid Checksum");
        }
        TicketLinks ticketLinks = new TicketLinks(ticket);
        ticket.add(ticketLinks.getAll());
        if (ticket.getInvalid() != null && ticket.getTicketStatus().getTicketStatus_id() != 1) {
            if (ticket.getInvalid() != null) {
                result.add(ticket);
                result.add("Ticket has been marked invalid");
                return result;
            } else {
                result.add(ticket);
                result.add("Invalid ticket status: " + ticket.getTicketStatus().getName());
                return result;
            }
        }
        ticket.setTicketStatus(tStatusRepository.findById(Integer.toUnsignedLong(2)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1")));
        result.add(tRepository.save(ticket));
        return result;
    }
}