package it.vwgroup.il.vwkafka.kafka.service;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import it.vwgroup.il.vwkafka.kafka.stream.BPStreams;

@Service
public class KafkaService {
	private final BPStreams bpStreams;

    public KafkaService(BPStreams bpStreams) {
        this.bpStreams = bpStreams;
    }

    public void sendGreeting(final String json) {
        //log.info("Sending greetings {}", json);

        MessageChannel messageChannel = bpStreams.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
