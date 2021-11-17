package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.MembershipLevel;
import be.parkalot.knight_parkalot.dto.CreateMemberDto;
import be.parkalot.knight_parkalot.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import be.parkalot.knight_parkalot.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    private final NameMapper nameMapper;
    private final AddressMapper addressMapper;
    private final LicensePlateMapper licensePlateMapper;
    private final MembershipLevelMapper membershipLevelMapper;

    @Autowired
    public MemberMapper(NameMapper nameMapper, AddressMapper addressMapper, LicensePlateMapper licensePlateMapper, MembershipLevelMapper membershipLevelMapper) {
        this.nameMapper = nameMapper;
        this.addressMapper = addressMapper;
        this.licensePlateMapper = licensePlateMapper;
        this.membershipLevelMapper = membershipLevelMapper;
    }

    public Member toEntity(CreateMemberDto createMemberDto, MembershipLevel membershipLevel) {
        return new Member(nameMapper.toEntity(createMemberDto.getNameDto()),
                addressMapper.toEntity(createMemberDto.getAddressDto()),
                createMemberDto.getTelephoneNumber(),
                createMemberDto.getEmail(),
                licensePlateMapper.toEntity(createMemberDto.getLicensePlateDto()),
                createMemberDto.getRegistrationDate(),
                membershipLevel);
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(nameMapper.toDto(member.getName()),
                addressMapper.toDto(member.getAddress()),
                member.getTelephoneNumber(),
                member.getEmail(),
                licensePlateMapper.toDto(member.getLicensePlate()),
                member.getRegistrationDate(),
                membershipLevelMapper.toDto(member.getMembershipLevel()));
    }
}
