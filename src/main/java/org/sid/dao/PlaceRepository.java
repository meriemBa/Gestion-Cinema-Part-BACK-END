package org.sid.dao;

import org.sid.entities.Place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface PlaceRepository extends JpaRepository<Place, Long> {
	
	
}
