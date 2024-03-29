package com.kstreams;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.KafkaContainer;
@Testcontainers
public class TestDataSetup implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
	private static boolean started = false;
	// @Container
	// public static GenericContainer<?> kafkaContainer = new GenericContainer<>(
	//         ("bitnami/kafka:3.6"))
	//         .withEnv("KAFKA_AUTO_CREATE_TOPICS_ENABLE", "true")
	//         .withEnv("KAFKA_CREATE_TOPICS", "kafka_topic");
	@Container
	public static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0")).withKraft();
	@Override
	public void close() throws Throwable {
		// Stop the kafka container
		kafkaContainer.stop();
	}
	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		if (started)
			return;
		// Fire up the kafka Container
		kafkaContainer.start();
		// Store this instance in the global context store
		context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("TestDataSetup", this);
	}
}