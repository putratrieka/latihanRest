package com.eksad.latihanrest.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainController {

	@RequestMapping("/admin")
	public HashMap<String, Object> admin(){
		
		HashMap<String, Object> map  = new HashMap<String, Object>();	
		map.put("Message", "Masuk Sebagai Admin");	
		return map;
	}
	@RequestMapping("/user")
	public HashMap<String, Object> user(){
		
		HashMap<String, Object> map  = new HashMap<String, Object>();	
		map.put("Message", "Masuk Sebagai User");	
		return map;
	}
	
}
