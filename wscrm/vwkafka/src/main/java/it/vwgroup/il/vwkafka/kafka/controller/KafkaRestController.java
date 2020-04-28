package it.vwgroup.il.vwkafka.kafka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.vwgroup.il.vwkafka.kafka.service.KafkaService;

@RestController
public class KafkaRestController {

	private final KafkaService kafkaService;

    public KafkaRestController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/sendKafka")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendKafka(@RequestBody String message) {
//        Greetings greetings = Greetings.builder()
//            .message(message)
//            .timestamp(System.currentTimeMillis())
//            .build();
        //Greetings greetings = new Greetings(System.currentTimeMillis(), message);

        System.out.println(message);
        kafkaService.sendGreeting(System.currentTimeMillis() + ": " + message);

        return true;
    }
}
