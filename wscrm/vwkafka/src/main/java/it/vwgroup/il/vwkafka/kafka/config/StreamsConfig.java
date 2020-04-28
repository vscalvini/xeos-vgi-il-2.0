package it.vwgroup.il.vwkafka.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import it.vwgroup.il.vwkafka.kafka.stream.BPStreams;

@EnableBinding(BPStreams.class)
public class StreamsConfig {
}
