package com.yedam.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	
//	BNO        NOT NULL NUMBER         
//	title      NOT NULL VARCHAR2(1000) 
//	contents   NOT NULL VARCHAR2(4000) 
//	WRITER     NOT NULL VARCHAR2(1000) 
//	REGDATE    NOT NULL DATE           
//	UPDATEDATE          DATE           
//	IMAGE               VARCHAR2(4000) 
	
	private int bno;
	private String title;
	private String contents;
	private String writer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedate;
	private String image;
}
