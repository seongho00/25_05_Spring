
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrAPITestController {

	@RequestMapping("/usr/home/APITest")
	public String showAPITest() {
		return "/usr/home/APITest";
	}

}