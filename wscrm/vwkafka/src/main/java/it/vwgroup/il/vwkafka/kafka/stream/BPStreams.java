package it.vwgroup.il.vwkafka.kafka.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BPStreams {
	//String INPUT = "";
    String OUTPUT = "topic-test";

//    @Input(INPUT)
//    SubscribableChannel inboundGreetings();

    @Output(OUTPUT)
    MessageChannel outboundGreetings();
}
