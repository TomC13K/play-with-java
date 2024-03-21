package config
//
//import org.springframework.context.annotation.Configuration
//import org.springframework.test.context.ActiveProfiles
//import org.testcontainers.containers.DockerComposeContainer
//import org.testcontainers.containers.wait.strategy.Wait
//import org.testcontainers.spock.Testcontainers
//
//@Testcontainers
//@ActiveProfiles('test')
//@Configuration
//class Config {
//
//    static final String DOCKER_COMPOSE_FILE_PATH = "src/test/resources/compose.yml"
//
//    static DockerComposeContainer environment = new DockerComposeContainer(new File(DOCKER_COMPOSE_FILE_PATH))
//            .withExposedService("kafka", 9092)
//}
