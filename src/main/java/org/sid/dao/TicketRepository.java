package org.sid.dao;

import java.util.List;

import org.sid.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	public List<Ticket> findAll();
}
