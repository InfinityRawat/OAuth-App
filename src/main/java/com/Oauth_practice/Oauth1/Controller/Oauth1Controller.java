package com.Oauth_practice.Oauth1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Oauth1Controller {

	
	@GetMapping("/public")
	public String publicPage() {
		
		return "public";
	}
	
	@GetMapping("/admin")
	public String adminPage() {
		return "Admin";
	}
}
