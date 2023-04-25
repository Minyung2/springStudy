package com.study.springboot;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonDto {
	private String name;
	private String age;
}
