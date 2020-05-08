package it.vwgroup.il.vwleads.service;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import it.vwgroup.il.vwleads.stream.LeadStreams;

@Service
public class LeadService {
	private final LeadStreams leadStreams;

    public LeadService(LeadStreams leadStreams) {
        this.leadStreams = leadStreams;
    }

    public void sendGreeting(final String json) {
        //log.info("Sending greetings {}", json);

        MessageChannel messageChannel = leadStreams.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
