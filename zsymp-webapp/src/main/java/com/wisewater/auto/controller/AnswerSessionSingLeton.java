package com.wisewater.auto.controller;

import java.util.HashMap;
import java.util.List;



public class AnswerSessionSingLeton {

	private HashMap<String, List<AnswerContext>> hashMap;
	
	private AnswerSessionSingLeton(){
		hashMap = new HashMap<String, List<AnswerContext>>();  
	}
	
	private static AnswerSessionSingLeton single = null;
	
	public static AnswerSessionSingLeton getInstance() {  
        if (single == null) {    
            single = new AnswerSessionSingLeton();  
        }    
       return single;  
   }

	public HashMap<String, List<AnswerContext>> getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap<String, List<AnswerContext>> hashMap) {
		this.hashMap = hashMap;
	}
	
}
