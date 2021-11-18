package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Address;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.AddressDto;
import be.parkalot.knight_parkalot.dto.PostalCodeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private final PostalCodeMapper postalCodeMapper = new PostalCodeMapper();
    private final AddressMapper addressMapper = new AddressMapper(postalCodeMapper);

    @Test
    void addressToDtoWorks() {
        //given
        Address address = new Address("Street", "25B", new PostalCode(1000, "Brussel"));
        AddressDto expectedDto = new AddressDto("Street", "25B", new PostalCodeDto(1000, "Brussel"));
        //when
        AddressDto resultDto = addressMapper.toDto(address);
        //then
        assertEquals(expectedDto, resultDto);
    }

    @Test
    void dtoToAddressWorks() {
        //given
        Address expected = new Address("Street", "25B", new PostalCode(1000, "Brussel"));
        AddressDto addressDto = new AddressDto("Street", "25B", new PostalCodeDto(1000, "Brussel"));
        //when
        Address result = addressMapper.toEntity(addressDto, postalCodeMapper.toEntity(addressDto.getPostalCodeDto()));
        //then
        assertEquals(expected, result);
    }

}