package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class HttpUtil {
	
	private String value;
	public HttpUtil(String value) {
		this.value = value;
	}
	
	public <E> E toModel(Class<E> eClass) {
		
		try {
			return new ObjectMapper().readValue(value, eClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new HttpUtil(sb.toString());
	}
}
