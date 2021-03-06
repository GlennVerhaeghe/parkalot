package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.address.PostalCodeDto;
import org.springframework.stereotype.Component;

@Component
public class PostalCodeMapper {

    public PostalCode toEntity(PostalCodeDto postalCodeDto) {
        return new PostalCode(postalCodeDto.getCode(), postalCodeDto.getCity());
    }

    public PostalCodeDto toDto(PostalCode postalCode) {
        return new PostalCodeDto(postalCode.getCode(), postalCode.getCity());
    }

}
