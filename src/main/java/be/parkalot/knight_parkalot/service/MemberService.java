package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.MembershipLevel;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.CreateMemberDto;
import be.parkalot.knight_parkalot.dto.LicensePlateDto;
import be.parkalot.knight_parkalot.dto.MemberDto;
import be.parkalot.knight_parkalot.dto.PostalCodeDto;
import be.parkalot.knight_parkalot.exceptions.DatabaseProblemException;
import be.parkalot.knight_parkalot.exceptions.NotUniqueException;
import be.parkalot.knight_parkalot.mapper.LicensePlateMapper;
import be.parkalot.knight_parkalot.mapper.MemberMapper;
import be.parkalot.knight_parkalot.mapper.PostalCodeMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.MembershipLevelRepository;
import be.parkalot.knight_parkalot.repository.PostalCodeRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.MemberInputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private static final int MEMBERSHIP_LEVEL_DEFAULT_BRONZE_VALUE = 1;

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MembershipLevelRepository membershipLevelRepository;
    private final LicensePlateMapper licensePlateMapper;
    private final PostalCodeRepository postalCodeRepository;
    private final PostalCodeMapper postalCodeMapper;
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper, MembershipLevelRepository membershipLevelRepository, LicensePlateMapper licensePlateMapper, PostalCodeRepository postalCodeRepository, PostalCodeMapper postalCodeMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.membershipLevelRepository = membershipLevelRepository;
        this.licensePlateMapper = licensePlateMapper;
        this.postalCodeRepository = postalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
    }

    public MemberDto registerMember(CreateMemberDto createMemberDto) {
        logger.info("registerMember() called");
        assertValidMemberDto(createMemberDto);
        assertIsMailUnique(createMemberDto.getEmail());
        assertLicensePlateIsUnique(createMemberDto.getLicensePlateDto());

        MembershipLevel membershipLevel = getMembershipLevel(createMemberDto.getMembershipLevelID());
        PostalCode postalCode = getPostalCode(createMemberDto.getAddressDto().getPostalCodeDto());

        Member member = memberRepository.save(memberMapper.toEntity(createMemberDto, membershipLevel, postalCode));
        return memberMapper.toDto(member);
    }

    public void assertValidMemberDto(CreateMemberDto createMemberDto) {
        logger.info("assertValidMemberDto called");
        MemberInputValidation memberInputValidation = new MemberInputValidation(createMemberDto);
        memberInputValidation.validate();
    }

    private PostalCode getPostalCode(PostalCodeDto postalCodeDto) {
        logger.info("getPostalCode called");
        Optional<PostalCode> postalCodeOptional = postalCodeRepository.findById(postalCodeDto.getCode());
        return postalCodeOptional.orElseGet(() -> postalCodeRepository.save(postalCodeMapper.toEntity(postalCodeDto)));
    }

    private MembershipLevel getMembershipLevel(int membershipId) {
        logger.info("getMembershipLevel() called");
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
}
