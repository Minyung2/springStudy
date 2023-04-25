package com.study.springboot.bean;


public class Member {
	private String name;
	private String nickname;
	private Printer printer;
	
	public Member() {}
	
	public Member(String name, String nickname, Printer priter) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.printer = priter;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Printer getPrinter() {
		return printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	public void print() {
		printer.print("안녕!" + name + ":" +  nickname);
	}
}
