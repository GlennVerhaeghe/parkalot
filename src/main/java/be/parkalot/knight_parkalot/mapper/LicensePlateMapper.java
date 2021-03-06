package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.dto.member.LicensePlateDto;
import org.springframework.stereotype.Component;

@Component
public class LicensePlateMapper {

    public LicensePlate toEntity(LicensePlateDto licensePlateDto) {
        return new LicensePlate(licensePlateDto.getNumber(), licensePlateDto.getCountryCode());
    }

    public LicensePlateDto toDto(LicensePlate licensePlate) {
        return new LicensePlateDto(licensePlate.getNumber(), licensePlate.getCountryCode());
    }

}
