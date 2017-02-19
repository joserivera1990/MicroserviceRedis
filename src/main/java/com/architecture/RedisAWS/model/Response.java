package com.architecture.RedisAWS.model;

import java.util.List;

public class Response {

	private List<Seller> sellers;
    private String code;
    private String message;
    
	public Response(List<Seller> sellers, String code, String message) {
		this.sellers = sellers;
		this.code = code;
		this.message = message;
	}
	
	public Response(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public List<Seller> getSellers() {
		return sellers;
	}
	
	public void setSeller(List<Seller> sellers) {
		this.sellers = sellers;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
