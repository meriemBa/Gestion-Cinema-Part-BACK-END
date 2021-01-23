package org.sid.web;



import javax.validation.Valid;

import org.sid.dao.TicketRepository;
import org.sid.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TicketController {
	@Autowired
	private TicketRepository ticketRepository;

	
	@GetMapping(path = "/index1")
	public String test(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		org.springframework.data.domain.Page<Ticket> pagetickets = ticketRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("tickets", pagetickets.getContent());
		model.addAttribute("pages", new int[pagetickets.getTotalPages()]);
		model.addAttribute("currentPage", page);

		return "test";
	}
	
	
	@GetMapping(path = "/delete")
	public String delete(int id, String keyword, int page) {

		ticketRepository.deleteById((long) id);

		return "redirect:/index1?page=" + page + "&keyword=" + keyword;
	}

	@GetMapping(path = "/formTickets")
	public String formTickets(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "formTickets";
	}
	@GetMapping(path = "/index")
	public String Home(Model model) {
		return "index";
	}
	@PostMapping(path = "/saveTicket")
	public String savePatient(@Valid Ticket ticket, BindingResult b, Model model) {
		if (b.hasErrors()) {

			model.addAttribute("ticket", ticket);
			return "formTickets";
		}
		// patient.setDate(new Date());
		ticketRepository.save(ticket);
		return "redirect:/index";
	}
	@GetMapping(path = "/edit")
	public String edit(Model model, int id) {
		Ticket ticket = ticketRepository.findById((long) id).get();
		model.addAttribute("ticket", ticket);
		return "formTickets";
	}
}
