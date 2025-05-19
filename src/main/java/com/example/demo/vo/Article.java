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
	int boardId;
	boolean userCanModify;
	boolean userCanDelete;
	int badReactionPoint;
	int goodReactionPoint;

	String extra__writer;
	String extra__boardName;
	int hitCount;

}
