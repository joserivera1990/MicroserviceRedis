package com.architecture.RedisAWS.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.architecture.RedisAWS.model.Response;
import com.architecture.RedisAWS.model.Seller;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class ShopInfo implements RequestHandler<Object, Object> {
   
    public Object handleRequest(Object input, Context context) {
		Jedis jedis = new Jedis("redisuser.jlrgpj.0001.use1.cache.amazonaws.com", 6379);
		Set<String> setKeys = jedis.keys("_*");
		List<Seller> sellers = setKeys.stream()
									  .map(k -> buildSeller(k, jedis))
									  .collect(Collectors.toList());
		if (sellers.size() == 0) {
			return new Response("1", "Sellers not found");
		}
		return new Response(sellers,"0","Transaction succesful ");
    }
    
    private Seller buildSeller(String key, Jedis jedis) {
    	try {
	    	ObjectMapper mapper = new ObjectMapper();
	    	String response = jedis.get(key);	
			return mapper.readValue(response, Seller.class);
		} catch (IOException e) {
			return new Seller();
		}
    }
}