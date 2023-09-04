package com.library.management.system.service;

import com.library.management.system.dao.MemberRepository;
import com.library.management.system.entities.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class) this module is from JUnit4 but for JUnit5 we don't need this annotation but instead we use following
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;




    @Test
    void testAddMember() {
        Member member = new Member(1L,"John Doe", "83264688890", new ArrayList<>(), LocalDate.now());
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member created = memberService.addMember(member);
        //assertEquals(created.getName(), "John Doe");
        assertEquals(created.getPhone(), "83264688890");

    }

    //In a Spring environment, bean validations (like @Size, @NotNull, etc.) specified on your entity fields will only be triggered when the entity
    // is being managed by the Spring context, usually during actual database operations or when you explicitly trigger validation.
    @Test
    void testAddMemberForPhoneValidation() {
        Member member = new Member(1L,"John Doe", "83264688890", new ArrayList<>(), LocalDate.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertFalse(violations.isEmpty());

        // Optional: Check if the correct field has a violation
        ConstraintViolation<Member> violation = violations.iterator().next();
        //assertEquals("phone", violation.getPropertyPath().toString());
        assertEquals("Invalid Phone Number Format.", violation.getMessage());

    }


    @Test
    void getByMemberId() {
    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void findByPhone() {
    }

    @Test
    void updateUsingPhone() {
    }

    @Test
    void deleteByPhone() {
    }
}