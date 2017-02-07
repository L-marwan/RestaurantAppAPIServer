package com.projet.util;

import java.util.UUID;

public class IdGenerator {
	
	
	/**
	 * generate globally unique IDs
	 * @return a globally unique Id string 
	 */
	public static String generate(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	

}
