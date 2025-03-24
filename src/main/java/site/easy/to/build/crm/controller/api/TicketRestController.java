package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.ticket.TicketService;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }
    @GetMapping("/total")
    public double getTotalTickets() {
        return ticketService.getSum();
    }

    @PutMapping("/update/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable int ticketId, @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketService.findByTicketId(ticketId);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        ticket.setSubject(ticketDetails.getSubject());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatus(ticketDetails.getStatus());
        ticket.setPriority(ticketDetails.getPriority());
      
       

        Ticket updatedTicket = ticketService.save(ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @PostMapping("/delete/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int ticketId) {
        Ticket ticket = ticketService.findByTicketId(ticketId);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        ticketService.deleteTicketWithDepenses(ticketId);
        return ResponseEntity.noContent().build();
    }
}