package com.library.management.system.service;

import com.library.management.system.dao.MemberRepository;
import com.library.management.system.entities.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
        //assertEquals("phone", violation.getPropertyPath().toString()); //this will check whether the field of phone is spell phone or not in Member entity
        assertEquals("Invalid Phone Number Format.", violation.getMessage());

    }

    //JUnit 5 provides @ParameterizedTest to run a test multiple times with different arguments.
    //It works in combination with one of the source annotations to provide input values for a single test.
    @ParameterizedTest
    @ValueSource(strings = { "8326468889", "83264688890", " ", "", "12345", "83264688A9" })
    void testAddMemberForPhoneValidation(String phoneNumber) {
        Member member = new Member(1L,"John Doe", phoneNumber, new ArrayList<>(), LocalDate.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertFalse(violations.isEmpty());

        // Optional: Check if the correct field has a violation
        ConstraintViolation<Member> violation = violations.iterator().next();
        System.out.println(violation.getMessage());
    }
   //When you run the above test it will throw first argument failed because it did not violate and assertFalse is in check which is expecting false
    //however it is true because it is not breaking any rule but for all other 5 argument run passed because all of them have somehow
    // breaks the rule and did violation.


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