package it.vwgroup.il.cap.service;

import java.util.List;

import it.vwgroup.il.cap.domain.Cap;
import it.vwgroup.il.cap.repository.CapRepository;

public class CapServiceImpl implements CapService{

	private CapRepository repository;
	
	@Override
	public Cap getCapById(String id) {
		return repository.getOne(id);
	}

	@Override
	public List<Cap> getCapByCode(String code) {
		return repository.findAll();
	}

}
