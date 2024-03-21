package com.kstreams
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.DockerComposeContainer
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

    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File(DOCKER_COMPOSE_FILE_PATH))
                    .withLocalCompose(true)
                    .withExposedService(9092)

    def "Kafka container should be running"() {
        when:
        environment.start()

        then:
        def kafkaPort = environment.getServicePort("kafka", 9092)
        kafkaPort.isPresent() == true
    }

}

