package com.javadwarf.kafka.basics;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerBasics1 {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        try {
            for (int i = 0; i < 100; i++) {
                ProducerRecord record = new ProducerRecord("replicated_topic", Integer.toString(i),
                        "Amit's Message " + Integer.toString(i));
                producer.send(record);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            producer.close();
        }
    }

}
