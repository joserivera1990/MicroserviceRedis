package com.architecture.RedisAWS.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.architecture.RedisAWS.util.TopicUtil;

public class PublishKafka implements RequestHandler<Object, Object> {
	   
public static final String KEY_SERIALIZAER = "org.apache.kafka.common.serialization.StringSerializer";
    
    private static Properties props;
   	
    //basic, silver, gold, platinum, black 
    
	public Object handleRequest(Object input, Context context) {
    	
		JSONObject request = null;
		props = new Properties();
        props.put("bootstrap.servers", "172.31.64.4:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", KEY_SERIALIZAER);
        props.put("value.serializer", KEY_SERIALIZAER);
		
        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
        	request = new JSONObject(input.toString());
        	String topic = TopicUtil.getTopic(request.getString("category"));
        	String identificationNumber = request.getString("identificationNumber");
        	//ObjectMapper objectMapper = new ObjectMapper();
            //json = objectMapper.writeValueAsString(input.toString());
            producer.send(new ProducerRecord<>(topic,
            		identificationNumber, 
                    request.getString("body")));
        } catch (JSONException e) {
        	return e.toString();
        }
		
		return  "OK";
    }
    
}
