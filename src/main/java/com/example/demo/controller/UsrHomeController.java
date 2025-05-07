package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;

@Controller
public class UsrHomeController {

	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map<String, Object> getMap() {
		Map<String, Object> testMap = new HashMap();
		testMap.put("id", "1");
		testMap.put("title", "제목1");
		testMap.put("body", "내용1");

		return testMap;
	}

	@RequestMapping("/usr/home/getDouble")
	@ResponseBody
	public Double getDouble() {
		Double testDouble = 3.141592;

		return testDouble;
	}

	@RequestMapping("/usr/home/getBoolean")
	@ResponseBody
	public Boolean getBoolean() {
		Boolean testBoolean = true;

		return testBoolean;
	}

	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		int id = 1;
		String title = "제목1";
		String body = "내용1";
		Article testBoolean = new Article(id, title, body);

		return testBoolean;
	}

}
