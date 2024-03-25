package config

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.GenericContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.AutoCleanup
import spock.lang.Shared


@Testcontainers
@ActiveProfiles('test')
@Configuration
class Config {

    private static boolean isStarted = false;

    @Shared
    @AutoCleanup
    public static GenericContainer<?> environment = new GenericContainer<>("bitnami/kafka:3.6")
            .withEnv("KAFKA_ENABLE_RAFT", "yes")
            .withEnv("KAFKA_CFG_PROCESS_ROLES", "broker,controller")
            .withEnv("KAFKA_CFG_CONTROLLER_LISTENER_NAMES", "CONTROLLER")
            .withEnv("KAFKA_CFG_LISTENERS", "PLAINTEXT://:9092,CONTROLLER://:9093")
            .withEnv("KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP", "CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT")
            .withEnv("KAFKA_CFG_ADVERTISED_LISTENERS", "PLAINTEXT://localhost:9092")
            .withEnv("KAFKA_BROKER_ID", "1")
            .withEnv("KAFKA_KRAFT_CLUSTER_ID", "LelM2dIFQkiUFvXCEcqRWA")
            .withEnv("KAFKA_CFG_CONTROLLER_QUORUM_VOTERS", "1@localhost:9093")
            .withEnv("ALLOW_PLAINTEXT_LISTENER", "yes")
            .withEnv("KAFKA_CFG_NODE_ID", "1;");

    static class LocalServerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        void initialize(final ConfigurableApplicationContext applicationContext) {
            if (isStarted)
                return;

            environment.start();
        }
    }
}
