package fi.rbmk.ticketguru.ticket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import fi.rbmk.ticketguru.eventTicket.EventTicket;
import fi.rbmk.ticketguru.eventTicket.EventTicketRepository;
import fi.rbmk.ticketguru.saleRow.SaleRow;
import fi.rbmk.ticketguru.saleRow.SaleRowRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SaleRowRepository saleRowRepository;

    @Autowired
    EventTicketRepository eventTicketRepository;

    @Test
    public void whenCreated_returnTickets() {
        SaleRow saleRow = saleRowRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException());
        EventTicket eventTicket = eventTicketRepository.findById(Integer.toUnsignedLong(1)).orElseThrow(() -> new ResourceNotFoundException());
    
        ResponseEntity<?> ticketList = ticketService.generateTickets(saleRow, eventTicket, Integer.toUnsignedLong(10));

        assertEquals(10, ((List<Ticket>)ticketList.getBody()).size());
        for (Ticket ticket : ((List<Ticket>)ticketList.getBody())) {
            assertEquals(null, ticket.getInvalid());
            assertNotEquals(null, ticket.getChecksum());
            assertNotEquals(null, ticket.getCreated());
            assertEquals("Voimassa", ticket.getTicketStatus().getName());
            assertEquals(saleRow, ticket.getSaleRow());
            assertEquals(eventTicket, ticket.getEventTicket());
        }
    }
    
}