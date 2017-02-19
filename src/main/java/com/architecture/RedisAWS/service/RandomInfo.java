package com.architecture.RedisAWS.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.architecture.RedisAWS.model.Response;
import com.architecture.RedisAWS.model.Seller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class RandomInfo implements RequestHandler<Object, Object> {

    public Object handleRequest(Object input, Context context) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			Jedis jedis = new Jedis("redisuser.jlrgpj.0001.use1.cache.amazonaws.com", 6379);
			Seller seller = new Seller(2, 1030583888, "PEDRO", "RIVERA", "ing@gmail.com", 2934561, 20);
			String request = mapper.writeValueAsString(seller);
			jedis.set("_1030583888",request);
			saveSeller();
			return request;
		} catch (JsonProcessingException e1) {
			return new Response("1", "Error in the serielization of the data");
		} 
    }
    
    private void saveSeller() throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	for (int i = 1000000000; i < 1000001000; i++) {
			Jedis jedis = new Jedis("redisuser.jlrgpj.0001.use1.cache.amazonaws.com", 6379);
			Seller seller = new Seller(2, i, "PEDRO", "RIVERA", "ing@gmail.com", 2934561, 20);
			String request = mapper.writeValueAsString(seller);
			jedis.set("_"+i,request);
		}
    }
   
}