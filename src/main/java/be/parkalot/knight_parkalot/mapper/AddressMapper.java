package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Address;
import be.parkalot.knight_parkalot.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private PostalCodeMapper postalCodeMapper;

    @Autowired
    public AddressMapper(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }

    public Address toEntity(AddressDto addressDto) {
        return new Address(addressDto.getStreetName(), addressDto.getHouseNumber(), postalCodeMapper.toEntity(addressDto.getPostalCodeDto()));
    }

    public AddressDto toDto(Address address) {
        return new AddressDto(address.getStreetName(), address.getHouseNumber(), postalCodeMapper.toDto(address.getPostalCode()));
    }
}
