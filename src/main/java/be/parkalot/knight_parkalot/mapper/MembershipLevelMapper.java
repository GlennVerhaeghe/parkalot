package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.MembershipLevel;
import be.parkalot.knight_parkalot.dto.member.MembershipLevelDto;
import org.springframework.stereotype.Component;

@Component
public class MembershipLevelMapper {

    public MembershipLevel toEntity(MembershipLevelDto membershipLevelDto) {
        return new MembershipLevel(membershipLevelDto.getName(), membershipLevelDto.getMonthlyCost(), membershipLevelDto.getReductionPercentage(), membershipLevelDto.getMaxAllowedAllocationHours());
    }

    public MembershipLevelDto toDto(MembershipLevel membershipLevel) {
        return new MembershipLevelDto(membershipLevel.getName(), membershipLevel.getMonthlyCost(), membershipLevel.getReductionPercentage(), membershipLevel.getMaxAllowedAllocationHours());
    }

}
