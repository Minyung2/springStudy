package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;

    public Member createMember(String email,String password){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setPassword(password);
        memberFormDto.setName("테스트");
        memberFormDto.setAddress("서울");
        Member member = Member.createMember(memberFormDto,passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인테스트")
    public void loginSuccessTest() throws Exception{
        String email = "tester@email.com";
        String password = "1234";
        this.createMember(email,password);
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                .userParameter("email")
                .loginProcessingUrl("/members/login")
                .user(email).password(password)
                ).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("실패 테스트")
    public void loginFailTest() throws Exception{
        String email = "tester@email.com";
        String password = "1234";
        this.createMember(email,password);
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                .userParameter("email")
                .loginProcessingUrl("/members/login")
                .user(email).password("12345")
        ).andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}