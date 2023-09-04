package com.library.management.system.controller;

import com.library.management.system.entities.Member;
import com.library.management.system.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@ExtendWith(MockitoExtension.class)
//@WebMvcTest(MemberController.class)// if you are only trying to test the Controller layer than you can use only this annotation
//and you don't need @SpringBootTest @ExtendWith(MockitoExtension.class) and even @BeforeEach setup() method and WebApplication context as well.
//but since you are doing integration testing than you have to have @SpringBootTest @ExtendWith(MockitoExtension.class)

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    void addMember() {
    }




    @Test
    void testGetMember() throws Exception {
        Member member = new Member(1L, "John Doe", "8324467388", new ArrayList<>(), LocalDate.now());

        when(memberService.getByMemberId(1L)).thenReturn(member);
        mockMvc.perform(get("/member/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }



    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void getMemberByPhone() {
    }

    @Test
    void updateMemberByPhone() {
    }

    @Test
    void deleteMemberByPhone() {
    }
}