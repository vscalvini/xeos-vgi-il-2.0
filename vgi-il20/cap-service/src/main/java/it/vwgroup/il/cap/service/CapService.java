package it.vwgroup.il.cap.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.vwgroup.il.cap.domain.Cap;

public interface CapService {

	Cap getCapById (String id);
	
	@Query("SELECT * FROM T_CN_CAP c WHERE c.CAP LIKE %:CAP%")
	List<Cap> getCapByCode (@Param("CAP") String code);
}
