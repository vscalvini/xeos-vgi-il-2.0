package it.vwgroup.il.cap.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.vwgroup.il.cap.domain.Cap;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CapRepositoryTest {

	@Autowired
	private CapRepository capRepository;
	
	@Test
	@Autowired
	public void shouldFindCapById() {

		Cap cap = new Cap();
		cap.setCap("25014");
		capRepository.getCapById(cap.getCap());

		Optional<Cap> found = capRepository.findById(cap.getCap());
		assertTrue(found.isPresent());
		assertEquals(cap.getCap(), found.get().getCap());
	}
}
