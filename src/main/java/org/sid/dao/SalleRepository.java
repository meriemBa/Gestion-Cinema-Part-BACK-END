package org.sid.dao;

import org.sid.entities.Salle;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface SalleRepository extends JpaRepository<Salle, Long> {
	public Page<Salle> findByNameContains(String mc, Pageable pageable);
}
