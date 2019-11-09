package com.capgemini.dnd.util;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static Logger logger = Logger.getRootLogger();
	static ObjectMapper objectMapper = new ObjectMapper();
	static JsonUtil jsonObject = new JsonUtil();
	static Message messageObject = jsonObject.new Message();

	public static String convertJavaToJson(Object object) {
		String jsonString = "";

		try {
			messageObject.setMessage(object.toString());
			jsonString = objectMapper.writeValueAsString(messageObject);
		} catch (JsonProcessingException exception) {
			logger.error(exception.getMessage());
		}

		return jsonString;
	}

	public static String convertJavaToJson1(Object object) {
		String jsonString = "";

		try {
			jsonString = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException exception) {
			logger.error(exception.getMessage());
		}

		return jsonString;
	}

	public static String convertJavaToJson(String str) {
		String jsonString = "";
		try {
			messageObject.setMessage(str);
			jsonString = objectMapper.writeValueAsString(messageObject);
		} catch (JsonProcessingException exception) {
			logger.error(exception.getMessage());
		}
		return jsonString;
	}

	class Message {
		private String message;

		public Message() {

		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

}