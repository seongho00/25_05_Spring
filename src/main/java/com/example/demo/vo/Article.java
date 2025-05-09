package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String title;
	String body;
	String name;
	int memberId;
}
