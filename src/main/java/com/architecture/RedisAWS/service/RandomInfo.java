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
			saveSeller();
			return "Created";
		} catch (JsonProcessingException e1) {
			return new Response("1", "Error in the serielization of the data");
		} 
    }
    
    private void saveSeller() throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	for (int i = 1000000000; i < 1000001000; i++) {
    		String name = getName(getRandomNumber(5));
    		String email = "ing." + name + i + "@gmail.com";
    		Jedis jedis = new Jedis("redisuser.jlrgpj.0001.use1.cache.amazonaws.com", 6379);
			Seller seller = new Seller(getRandomNumber(5), i, name, getApellido(getRandomNumber(5)), email, getRandomNumber(1000000), getRandomNumberMajor(60));
			String request = mapper.writeValueAsString(seller);
			jedis.set("_"+i,request);
		}
    }
    
    public static int getRandomNumber(int maxNumber) {
		return (int) (Math.random() * maxNumber) + 1;
	}
    
    public static int getRandomNumberMajor(int maxNumber) {
		return (int) (Math.random() * maxNumber) + 18;
	}
    
    public String getName(int maxNumber) {
		switch (maxNumber) {
		case 1:
			return "Karen";
		case 2:
			return "Sergio";
		case 3:
			return "Jose";
		case 4:
			return "Ricardo";
		case 5:
			return "Gabriel";
		default:
	        return "Dario";
		}
    }
    
    public String getApellido(int maxNumber) {
		switch (maxNumber) {
		case 1:
			return "Vega";
		case 2:
			return "Leottau";
		case 3:
			return "Badillo";
		case 4:
			return "Mestre";
		case 5:
			return "Rivera";
		default:
	        return "Martinez";
		}
    }
}