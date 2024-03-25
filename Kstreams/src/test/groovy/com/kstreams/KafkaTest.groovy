package com.kstreams

import config.Config
import one.kafkastreams.KafkaApp
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification


@ContextConfiguration(initializers = Config.LocalServerInitializer.class, classes = KafkaApp.class)
@ActiveProfiles(["test"])
@SpringBootTest(classes = KafkaApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KafkaTest extends Specification {

    def "Dummy test"() {
    expect:
        1 == 1
    }


//    def "Kafka container should be running"() {
//        println("TESTS HERE");
//        given:
//            String topic = "testTopic";
//
//
//        when:
//        println(Config.environment.exposedPorts);
//        var kafkaPort = Config.environment.getServicePort("kafka", 9092)
//
//        then:
//        //kafkaPort == 9092
//        1==1
//
//    }

}

