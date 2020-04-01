package fi.rbmk.ticketguru.ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    BCrypt bCrypt;

    public List<Ticket> generateTickets(SaleRow saleRow, EventTicket eventTicket, Long count) {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        TicketStatus ticketStatus = tStatusRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException("Invalid ID: 1"));
        for (int i = 0; i < count; i++) {
            String checkSum = bCrypt.hashpw(LocalDateTime.now().toString(), bCrypt.gensalt());
            Ticket ticket = new Ticket();
            ticket.setEventTicket(eventTicket);
            ticket.setTicketStatus(ticketStatus);
            ticket.setCheckSum(checkSum);
            ticket.setSaleRow(saleRow);
            ticketList.add(tRepository.save(ticket));
        }
        return ticketList;
    }
}