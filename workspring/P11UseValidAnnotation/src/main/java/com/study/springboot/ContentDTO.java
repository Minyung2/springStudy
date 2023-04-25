package com.study.springboot;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ContentDTO {
	private int id;
	@NotNull(message = "입력해라")
	@NotEmpty(message = "입력해라")
	@Size(min=2,max=20,message="writer 최소 두자 최대 열자")
	private String writer;
	@NotNull(message = "입력해라")
	@NotEmpty(message = "입력해라")
	@Size(min=2,max=20,message="content 최소 두자 최대 열자")
	private String content;
}