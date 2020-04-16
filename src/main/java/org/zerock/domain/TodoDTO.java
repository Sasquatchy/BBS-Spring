package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {

	private String title;
	
	//initBinder를 쓰지않고 이렇게 자동처리할수 있게 할수 ㅇㅆ다.
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
