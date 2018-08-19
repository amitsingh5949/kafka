package com.javadwarf.kafka.basics;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

public class ConsumerBasics1 {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
     //   props.put("enable.auto.commit", "true");
    //    props.put("auto.commit.interval.ms", "1000");
      //  props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put("partition.assignment.strategy", "0");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("replicated_topic"));

        try {
            do {
                ConsumerRecords<String, String> records = consumer.poll(10);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.topic() + " - " + record.partition()
                            + record.offset() + " - " + record.key() + " - " + record.value());
                }
            } while (true);
                  }
                  catch (Exception e){
            e.printStackTrace();
                  }
                  finally {
            consumer.close();
        }


    }
}
