package org.sid.dao;

import org.sid.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface FilmRepository extends JpaRepository<Film, Long> {

	
	public Page<Film> findByTitreContains(String mc, Pageable pageable);

	
}
