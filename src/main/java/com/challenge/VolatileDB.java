package com.challenge;

import java.util.LinkedHashMap;
import java.util.Map;

public class VolatileDB {
	
	static Map<String, User> database = new LinkedHashMap<String, User>();
	
	public static void insert (String id, User row) {
		
		database.put(id, row);
	}
	
	public static User fetch( String id) {
		
		return database.get(id);
		
	}

}
