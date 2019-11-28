package com.leeleo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年11月28 00时19分
 */
public class Producer {


    public static final String TOPIC = "leolee";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String,String> config = new HashMap<>() ;
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.2.5:9092,192.168.2.7:9092,192.168.2.9:9092");
        config.put(ProducerConfig.CLIENT_ID_CONFIG,"kafkaProducer1");
        config.put(ProducerConfig.ACKS_CONFIG,"-1");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.BATCH_SIZE_CONFIG,"10");

        KafkaProducer producer = new KafkaProducer(config);
        for(int i=0;i<100;i++){
            ProducerRecord<Integer,String> record = new ProducerRecord<>(TOPIC,i,"kafka"+i);
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata recordMetadata = future.get();
            System.out.println("发送消息: "+ recordMetadata.topic() + " " + recordMetadata.partition() + " " + recordMetadata.offset());
        }
    }
}
