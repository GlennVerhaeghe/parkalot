package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public MemberDto registerMember(MemberDto memberDto) {

        return memberDto;
    }

    public boolean assertValidMemberDto(MemberDto memberDto) {
        MemberInputValidation memberInputValidation = new MemberInputValidation(memberDto);
        return memberInputValidation.validate();
    }
}
