package com.library.management.system.controller;


import com.library.management.system.entities.Member;
import com.library.management.system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.addMember(member), HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberService.getByMemberId(memberId);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member member) {
        Member updatedMember = memberService.updateMember(memberId, member);
        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/phone/{phone}")
    public Member getMemberByPhone(@PathVariable String phone){
        return memberService.findByPhone(phone);
    }


    @PutMapping("/phone/{phone}")
    public Member updateMemberByPhone(@PathVariable String phone, @Valid @RequestBody Member member){
        return memberService.updateUsingPhone(phone, member);
    }


    @DeleteMapping("/phone/{phone}")
    public ResponseEntity<Void> deleteMemberByPhone(@PathVariable String phone){
        memberService.deleteByPhone(phone);
        return ResponseEntity.ok().build();
    }


}
