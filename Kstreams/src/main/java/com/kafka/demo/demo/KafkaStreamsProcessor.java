package com.kafka.demo.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;


/*
 using the application.yaml config
 RUN this and the producer separately
*/
public class KafkaStreamsProcessor {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kstreams-id-group");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> source = builder.stream("input-topic");

		// Process the messages
		KStream<String, String> processedStream = source.mapValues(value -> {
			// Do something with the message
			System.out.println(value);
			return "Processed: " + value.toUpperCase();
		});

		processedStream.to("output-topic");

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();

		// Add shutdown hook to cleanly close the streams application
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}

}

