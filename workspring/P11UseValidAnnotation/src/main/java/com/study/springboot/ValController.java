package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class ValController {
	@RequestMapping("/")
	public String root() {
		return "insertForm";
	}
	
	@RequestMapping("/done")
	public String insert(@ModelAttribute("dto") @Valid ContentDTO contentDto, BindingResult result) {
		System.out.println(contentDto);
		if(result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());
			if(result.getFieldError("writer")!=null) {
				System.out.println(result.getFieldError().getCode());
			}
			if(result.getFieldError("content")!=null) {
				System.out.println(result.getFieldError().getCode());
			}
			return "insertForm";
		}
		return "donePage";
	}
}
