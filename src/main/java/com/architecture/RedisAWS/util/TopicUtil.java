package com.architecture.RedisAWS.util;

public final class TopicUtil {

	private TopicUtil(){}
	
	public static String getTopic(String category) {
		switch (category) {
		case "1":
			return "basic";
		case "2":
			return "black";
		case "3":
			return "silver";
		case "4":
			return "platinum";
		case "5":
			return "gold";	
		default:
	          throw new AssertionError("Topic doesn't exist");
		}
	}
}
