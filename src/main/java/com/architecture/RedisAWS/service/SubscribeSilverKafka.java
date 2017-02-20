package com.architecture.RedisAWS.service;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SubscribeSilverKafka implements RequestHandler<Object, Object>{

public static final String KEY_SERIALIZAER = "org.apache.kafka.common.serialization.StringDeserializer";
    
    private static Properties props;
   	
    //basic, black, silver, platinum, gold 
      
	public Object handleRequest(Object input, Context context) {
		AuditMessage registerMessage = new AuditMessage();
		props = new Properties();
        props.put("bootstrap.servers", "172.31.64.4:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");        
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        try( KafkaConsumer<String, String> consumer = new KafkaConsumer<>( props )) { 
        	consumer.subscribe(Collections.singletonList("silver")); 
        	while (true) {
            	ConsumerRecords<String, String> records = consumer.poll(1000);
            	System.out.println(records.count() + "count");
            	for (ConsumerRecord<String, String> record : records) {
            		System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());  
            		registerMessage.saveRegister(Integer.valueOf(3), record.value(), Integer.valueOf(record.key()), context);
            	}
        	}
        } 
    }	
}
