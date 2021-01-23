package org.sid.dao;


import org.sid.entities.ProjectionFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface ProjectionFilmRepository extends JpaRepository<ProjectionFilm, Integer>{


	//public Page<ProjectionFilm> findByPrixContains(double mc, Pageable pageable);

	public Page<ProjectionFilm> findByIdContains(String mc, Pageable pageable);
	

	
}
