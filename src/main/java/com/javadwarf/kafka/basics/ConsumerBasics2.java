package com.javadwarf.kafka.basics;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ConsumerBasics2 {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        TopicPartition tp = new TopicPartition("replicated_topic", 0);

        consumer.assign(Arrays.asList(tp));

        try {
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(10);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.topic() + " - " + record.partition()
                            + record.offset() + " - " + record.key() + " - " + record.value());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            consumer.close();
        }

    }
}
