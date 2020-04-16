package org.zerock.domain;

import lombok.Data;

//DTO같이 짧은 수명의 클래스는 bean에 추가안함.
@Data
public class SampleDTO {

	private String name;
	private int age;
}
