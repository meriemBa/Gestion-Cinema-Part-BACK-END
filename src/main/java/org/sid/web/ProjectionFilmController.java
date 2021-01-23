package org.sid.web;


import javax.validation.Valid;

import org.sid.dao.ProjectionFilmRepository;
import org.sid.entities.Categorie;
import org.sid.entities.ProjectionFilm;
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
public class ProjectionFilmController {
	
	@Autowired
	private ProjectionFilmRepository projectionRepository;
	
	@GetMapping("/projection")
	public String projection(Model model,
		@RequestParam(name="page",defaultValue = "0")int page,
		@RequestParam(name="size",defaultValue = "5")int size,
		@RequestParam(name="keyword",defaultValue = "")String mc
	) {
		Page<ProjectionFilm> pageProjection=projectionRepository.findByIdContains(mc, PageRequest.of(page, size));
		model.addAttribute("listProjection", pageProjection.getContent());
		model.addAttribute("pages",new int[pageProjection.getTotalPages()]);
		
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("keyword",mc);
		
		
		return "projection";
	}
	
	/*
	 * @GetMapping(path="/deleteProjection") public String delete(Long id,String
	 * keyword,int page,int size) {
	 * 
	 * projectionRepository.deleteById(id);
	 * 
	 * return "redirect:/projection?page="+page+"&size="+size+"&keyword="+keyword; }
	 */
	/*
	 * @GetMapping(path="/formProjection") public String formProjection(Model model)
	 * { model.addAttribute("projection",new ProjectionFilm());
	 * model.addAttribute("mode","new"); return "formProjection"; }
	 * 
	 * @PostMapping(path="/saveProjection") public String saveProjection(Model
	 * model,@Valid ProjectionFilm projection, BindingResult bindingResult) {
	 * if(bindingResult.hasErrors()) {return "formCategory";}
	 * model.addAttribute("projection",projection);
	 * projectionRepository.save(projection); return "confirmationProjection"; }
	 * 
	 * @GetMapping(path="/editProjection") public String editProjection(Model model
	 * ,Long id) { ProjectionFilm f= projectionRepository.findById(id).get();
	 * model.addAttribute("categorie",f); model.addAttribute("mode","edit"); return
	 * "formProjection"; }
	 */
}
