package org.sid.web;


import javax.validation.Valid;

import org.sid.dao.FilmRepository;
import org.sid.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {
	
	@Autowired
	private FilmRepository filmRepository;
	@GetMapping("/film")
	public String film(Model model,
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="keyword",defaultValue = "")String mc 
	){ 
		Page<Film> pageFilm=filmRepository.findByTitreContains(mc,PageRequest.of(page, size));
		model.addAttribute("listFilms",pageFilm.getContent());
		model.addAttribute("pages",new int[pageFilm.getTotalPages()]);
		
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("keyword",mc);
		
		/*
		 * List<Film> films= filmRepository.findAll(); model.addAttribute("listPatients",films);
		 */
		
		return "film";
	}
	  @GetMapping(path="/home")
	  public String home() {	
		  return "home"; 
	  }
	  
	  @GetMapping(path="/deleteFilm")
	  public String delete(Long id,String keyword,int page,int size) {
		 
		  filmRepository.deleteById(id);

		  return "redirect:/film?page="+page+"&size="+size+"&keyword="+keyword; 
	  }
	 
	  @GetMapping(path="/formFilm")
	  public String formFilm(Model model) {
		  model.addAttribute("film",new Film());
		  model.addAttribute("mode","new");
		  return "formFilm"; 
	  }
	
	  @PostMapping(path="/saveFilm")
	  public String saveFilm(Model model,@Valid Film film, BindingResult bindingResult) {
		  if(bindingResult.hasErrors()) {return "formFilm";}
		  model.addAttribute("film",film);
		  filmRepository.save(film);
		  return "confirmationFilm"; 
	  }
	  
	  @GetMapping(path="/editFilm")
	  public String editFilm(Model model ,Long id) {
		  Film f= filmRepository.findById(id).get();
		  model.addAttribute("film",f);
		  model.addAttribute("mode","edit");
		  return "formFilm"; 
	  }

}
