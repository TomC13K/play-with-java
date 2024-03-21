package com.kstreams;

import com.kafka.demo.demo.KafkaStreamsProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(KafkaStreamsProcessor::main).with(TestDemoApplication.class).run(args);
	}

}
