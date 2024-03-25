package two.kstreamSeparate;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {
    private final static String TOPIC_NAME = "input-topic";

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            while (true) {
                String id = String.valueOf(System.currentTimeMillis());
                String name = "Name_" + id;
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, id, name);
                producer.send(record);
                Thread.sleep(1000); // Sending message every second
            }
        } finally {
            producer.close();
        }
    }
}

