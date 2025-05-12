package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	int memberId;
	boolean userCanModify;
	boolean userCanDelete;
	String extra__writer;

}
