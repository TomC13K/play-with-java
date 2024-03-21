package com.kstreamSeparate;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import java.util.Properties;

public class Processor {
    private final static String INPUT_TOPIC = "input-topic";
    private final static String OUTPUT_TOPIC = "output-topic";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "ks-id-group");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream(INPUT_TOPIC);

        // Apply filtering based on ID
        KStream<String, String> filteredStream = source.filter((key, value) -> {
            // Change the condition as per your requirements
            // Here, we are filtering out messages where the ID is less than 1600
            int id = Integer.parseInt(key);
            return id < 10;
        });

        filteredStream.to(OUTPUT_TOPIC);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        // Shutdown hook to correctly close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}

