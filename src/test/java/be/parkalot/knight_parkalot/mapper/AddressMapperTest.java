package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Address;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.AddressDto;
import be.parkalot.knight_parkalot.dto.PostalCodeDto;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private AddressMapper addressMapper = new AddressMapper(new PostalCodeMapper());

    @Test
    void addressToDtoWorks() {
        //given
        Address address = new Address("Street", "25B", new PostalCode(1000, "Brussel"));
        AddressDto resultDto = new AddressDto("Street", "25B", new PostalCodeDto(1000, "Brussel"));
        //when
        AddressDto addressDto = addressMapper.toDto(address);
        //then
        assertEquals(resultDto.getStreetName(), addressDto.getStreetName());
        assertEquals(resultDto.getHouseNumber(), addressDto.getHouseNumber());
        assertEquals(resultDto.getPostalCodeDto().getCode(), addressDto.getPostalCodeDto().getCode());
        assertEquals(resultDto.getPostalCodeDto().getCity(), addressDto.getPostalCodeDto().getCity());
        assertEquals(resultDto.getStreetName(), addressDto.getStreetName());
    }

}