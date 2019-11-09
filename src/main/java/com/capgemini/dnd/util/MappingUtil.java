package com.capgemini.dnd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingUtil {
	@SuppressWarnings("unchecked")
	public static Map<String,String> convertJsonObjectToFieldValueMap(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {

		StringBuffer stringBuffer = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader bufferedReader = request.getReader();
		    while ((line = bufferedReader.readLine()) != null)
		      stringBuffer.append(line);
		  } catch (Exception e) {  }
		Map<String,String> fieldValueMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();
		
		fieldValueMap = objectMapper.readValue(stringBuffer.toString(), HashMap.class);
		return fieldValueMap;
	}

}
