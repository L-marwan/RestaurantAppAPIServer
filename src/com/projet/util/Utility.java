package com.projet.util;



public class Utility {
	
	public static boolean isNotNull(String s){
		return s != null && s.trim().length()>= 0;
	}
	
	/*public  static String constructJSON (Menu m) throws JsonProcessingException{
		ObjectMapper mapper= new ObjectMapper();
		return mapper.writeValueAsString(m);
	}*/
}
