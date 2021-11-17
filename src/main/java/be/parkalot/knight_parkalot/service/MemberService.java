package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.MembershipLevel;
import be.parkalot.knight_parkalot.dto.CreateMemberDto;
import be.parkalot.knight_parkalot.dto.MemberDto;
import be.parkalot.knight_parkalot.exceptions.DatabaseProblemException;
import be.parkalot.knight_parkalot.mapper.MemberMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.MembershipLevelRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.MemberInputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private static final int MEMBERSHIP_LEVEL_DEFAULT_BRONZE_VALUE = 1;

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MembershipLevelRepository membershipLevelRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper, MembershipLevelRepository membershipLevelRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.membershipLevelRepository = membershipLevelRepository;
    }

    public MemberDto registerMember(CreateMemberDto createMemberDto) {

        MembershipLevel membershipLevel = getMembershipLevel(createMemberDto.getMembershipLevelID());
        assertValidMemberDto(createMemberDto);
        Member member = memberRepository.save(memberMapper.toEntity(createMemberDto, membershipLevel));
        return memberMapper.toDto(member);
    }

    public void assertValidMemberDto(CreateMemberDto createMemberDto) {
        MemberInputValidation memberInputValidation = new MemberInputValidation(createMemberDto);
        memberInputValidation.validate();
    }

    private MembershipLevel getMembershipLevel(int membershipId) {
        Optional<MembershipLevel> membershipLevelOptional = membershipLevelRepository.findById(membershipId);

        if (membershipLevelOptional.isPresent()) {
            return membershipLevelOptional.get();
        } else {
            Optional<MembershipLevel> membershipLevelDefault = membershipLevelRepository.findById(MEMBERSHIP_LEVEL_DEFAULT_BRONZE_VALUE);
            if (membershipLevelDefault.isPresent()) {
                return membershipLevelDefault.get();
            }
            throw new DatabaseProblemException("Default value not available in the database");
        }
    }
}
