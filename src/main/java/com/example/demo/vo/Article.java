package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String title;
	String body;
}
