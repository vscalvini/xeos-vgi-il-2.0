package it.vwgroup.il.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.vwgroup.il.cap.domain.Cap;
import it.vwgroup.il.cap.service.CapService;

@RestController
public class CapController {
	@Autowired
	private CapService capService;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Cap> getCapById(@PathVariable String id) {
		return capService.getCapByCode(id);
	}
	
	@RequestMapping(path = "/{code}", method = RequestMethod.GET)
	public List<Cap> getCapByCode(@PathVariable String code) {
		return capService.getCapByCode(code);
	}

}
