package com.kstreams
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.ComposeContainer
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = ["spring.kafka.bootstrap-servers=\${kafka.bootstrap-servers}"])
@Testcontainers
class KafkaTest extends Specification {

    private static final String DOCKER_COMPOSE_FILE_PATH = "src/test/resources/compose.yml"

    @Shared
    @AutoCleanup
    public static ComposeContainer environment =
            new ComposeContainer(new File(DOCKER_COMPOSE_FILE_PATH))
                    .withLocalCompose(true)
                    .withOptions("--compatibility")
                    .withExposedService("kafka", 1, 9092, Wait.forListeningPort())

    def "Kafka container should be running"() {
        when:
        environment.start()

        then:
        def kafkaPort = environment.getServicePort("kafka", 9092)
        kafkaPort.isPresent() == true

        expect:
        1==1
    }

}

