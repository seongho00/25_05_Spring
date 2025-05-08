package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String loginId;
	String loginPw;
	String name;
	String email;
}
