package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.dto.MemberDto;
import be.parkalot.knight_parkalot.mapper.MemberMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.MemberInputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto registerMember(MemberDto memberDto) {
        assertValidMemberDto(memberDto);
        memberRepository.save(memberMapper.toEntity(memberDto));
        return memberDto;
    }

    public boolean assertValidMemberDto(MemberDto memberDto) {
        MemberInputValidation memberInputValidation = new MemberInputValidation(memberDto);
        return memberInputValidation.validate();
    }
}
