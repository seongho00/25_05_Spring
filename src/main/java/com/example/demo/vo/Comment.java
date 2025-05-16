package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	int id;
	String regDate;
	String updateDate;
	String body;
	int articleId;
	int memberId;

}
