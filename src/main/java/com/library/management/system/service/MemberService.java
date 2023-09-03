package com.library.management.system.service;


import com.library.management.system.dao.MemberRepository;
import com.library.management.system.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member addMember(Member member){
        return memberRepository.save(member);
    }

    public Member getByMemberId(Long memberId){
        Member member = memberRepository.findById(memberId).orElse(null);
        return member;
    }

    public Member updateMember(Long memberId, Member member){
        if(memberRepository.existsById(memberId)){
            member.setMember_id(memberId);
            return memberRepository.save(member);
        }
        return null; // Or throw an appropriate exception.
    }

    public void deleteMember(Long memberId){
     memberRepository.deleteById(memberId);
    }


    public Member findByPhone(String phone){
        return memberRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("Member not found using phone number" + phone));
    }

    public Member updateUsingPhone(String phone , Member updatedMember){
       Member existingMember = findByPhone(phone);
       existingMember.setName(updatedMember.getName());
       return memberRepository.save(existingMember);
    }

    public void deleteByPhone(String phone){
        Member existMember = findByPhone(phone);
        memberRepository.delete(existMember);
    }
}
