package be.parkalot.knight_parkalot.service;


import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.MembershipLevel;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.member.CreateMemberDto;
import be.parkalot.knight_parkalot.dto.member.LicensePlateDto;
import be.parkalot.knight_parkalot.dto.member.MemberDetailsDto;
import be.parkalot.knight_parkalot.dto.member.MemberDto;
import be.parkalot.knight_parkalot.exceptions.DatabaseProblemException;
import be.parkalot.knight_parkalot.exceptions.MemberNotFoundException;
import be.parkalot.knight_parkalot.exceptions.NotUniqueException;
import be.parkalot.knight_parkalot.mapper.LicensePlateMapper;
import be.parkalot.knight_parkalot.mapper.MemberMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.MembershipLevelRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.MemberInputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MembershipLevelRepository membershipLevelRepository;
    private final LicensePlateMapper licensePlateMapper;
    private final PostalCodeService postalCodeService;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper, MembershipLevelRepository membershipLevelRepository, LicensePlateMapper licensePlateMapper, PostalCodeService postalCodeService) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.membershipLevelRepository = membershipLevelRepository;
        this.licensePlateMapper = licensePlateMapper;
        this.postalCodeService = postalCodeService;
    }

    public MemberDto registerMember(CreateMemberDto createMemberDto) {
        logger.info("registerMember() called");
        assertValidMemberDto(createMemberDto);
        assertIsMailUnique(createMemberDto.getEmail());
        assertLicensePlateIsUnique(createMemberDto.getLicensePlateDto());

        MembershipLevel membershipLevel = getMembershipLevel(createMemberDto.getMembershipLevelID());
        PostalCode postalCode = postalCodeService.getPostalCode(createMemberDto.getAddressDto().getPostalCodeDto());

        Member member = memberRepository.save(memberMapper.toEntity(createMemberDto, membershipLevel, postalCode));
        return memberMapper.toDto(member);
    }

    public void assertValidMemberDto(CreateMemberDto createMemberDto) {
        logger.info("assertValidMemberDto called");
        MemberInputValidation memberInputValidation = new MemberInputValidation(createMemberDto);
        memberInputValidation.validate();
    }

    public List<MemberDetailsDto> getAllMembers() {
        return memberRepository.findAll().stream().map(memberMapper::toRetrieveMemberDto).collect(Collectors.toList());
    }

    public MemberDto getMemberDtoById(int memberId) {
        return memberMapper.toDto(getMemberById(memberId));
    }

    public Member getMemberById(int memberId) {
        assertIdExistsInDatabase(memberId);
        return memberRepository.findById(memberId).get();
    }

    public MemberDto changeMembershipLevel(int memberId, int newMembershipLevelId) {
        MembershipLevel membershipLevel = getMembershipLevel(newMembershipLevelId);
        Member member = getMemberById(memberId);
        member.setMembershipLevel(membershipLevel);
        return memberMapper.toDto(member);
    }

    private MembershipLevel getMembershipLevel(int membershipId) {
        logger.info("getMembershipLevel() called");
        Optional<MembershipLevel> membershipLevelOptional = membershipLevelRepository.findById(membershipId);

        if (membershipLevelOptional.isPresent()) {
            return membershipLevelOptional.get();
        } else {
            Optional<MembershipLevel> membershipLevelDefault = membershipLevelRepository.findById(MembershipLevel.BRONZE_ID);
            if (membershipLevelDefault.isPresent()) {
                return membershipLevelDefault.get();
            }
            throw new DatabaseProblemException("Default value not available in the database");
        }
    }

    private void assertIsMailUnique(String email) {
        logger.info("assertIsMailUnique called");

        if (memberRepository.findByEmail(email) != null) {
            throw new NotUniqueException("Email address already exists.");
        }
    }

    private void assertLicensePlateIsUnique(LicensePlateDto licensePlateDto) {
        logger.info("assertLicensePlateIsUnique() called");

        if (memberRepository.findByLicensePlate(licensePlateMapper.toEntity(licensePlateDto)) != null) {
            throw new NotUniqueException("License plate is already registered.");
        }
    }

    private void assertIdExistsInDatabase(int id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("No member found with id: " + id);
        }
    }
}
