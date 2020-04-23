package it.vwgroup.il.cap.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.vwgroup.il.cap.domain.Cap;
import it.vwgroup.il.cap.repository.CapRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CapServiceTest {
	
	@InjectMocks
	private CapServiceImpl capService;

	@Mock
	private CapRepository repository;

	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void shouldReturnCapInformationById() {

		Cap cap = new Cap();
		cap.setCap("25014");

		capService.getCapByCode(cap.getCap());
		verify(repository, times(1)).getOne(cap.getCap());
	}
	
	@Test
	public void shouldReturnCapInformationByCode() {

		Cap cap = new Cap();
		cap.setCap("250");

		capService.getCapByCode(cap.getCap());
		verify(repository, times(1)).findAll();
	}
	
}
