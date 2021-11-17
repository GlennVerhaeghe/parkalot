package be.parkalot.knight_parkalot.mapper;

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

    public Member toEntity(MemberDto memberDto) {
        return new Member(nameMapper.toEntity(memberDto.getNameDto()),
                addressMapper.toEntity(memberDto.getAddressDto()),
                memberDto.getTelephoneNumber(),
                memberDto.getEmail(),
                licensePlateMapper.toEntity(memberDto.getLicensePlateDto()),
                memberDto.getRegistrationDate(),
                membershipLevelMapper.toEntity(memberDto.getMembershipLevelDto()));
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
