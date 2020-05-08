package it.vwgroup.il.vwleads.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.vwgroup.il.vwleads.service.LeadService;

@RestController
public class LeadRestController {

	private final LeadService leadService;

    public LeadRestController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping("/sendLead")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendLead(@RequestBody String message) {
//        Greetings greetings = Greetings.builder()
//            .message(message)
//            .timestamp(System.currentTimeMillis())
//            .build();
        //Greetings greetings = new Greetings(System.currentTimeMillis(), message);

        System.out.println(message);
        leadService.sendGreeting(System.currentTimeMillis() + ": " + message);

        return true;
    }
}
