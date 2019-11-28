package com.leeleo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年11月28 00时19分
 */
public class Consumer {


    public static final String TOPIC = "leolee";
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.2.5:9092,192.168.2.7:9092,192.168.2.9:9092");
        config.put(ConsumerConfig.CLIENT_ID_CONFIG,"kafkaConsumer1");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"kafkaConsumerGroup1");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer consumer = new KafkaConsumer(config);
        consumer.subscribe(Collections.singletonList(TOPIC));
        while (true){
            ConsumerRecords<Integer, String> poll = consumer.poll(1000);
            for(ConsumerRecord consumerRecord:poll){

                System.out.println("received message:"+consumerRecord.value() + ">>>"+atomicInteger.incrementAndGet());
            }
        }

    }
}
