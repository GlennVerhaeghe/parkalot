package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Address;
import be.parkalot.knight_parkalot.domain.ContactPerson;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.ContactPersonDto;
import be.parkalot.knight_parkalot.dto.CreateContactPersonDto;
import be.parkalot.knight_parkalot.dto.NameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPersonMapperTest {

    private ContactPersonMapper contactPersonMapper;
    private AddressMapper addressMapper;
    private NameMapper nameMapper;

    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapper(new PostalCodeMapper());
        nameMapper = new NameMapper();
        contactPersonMapper = new ContactPersonMapper(addressMapper, nameMapper);
    }
    @Test
    void toEntity() {
        //given
        Address address = new Address("Street", "1", new PostalCode(1000, "Brussel"));
        ContactPerson expected = new ContactPerson(new Name("Tom", "Hanks"), "112", "112", "test@gmail.com", address);
        CreateContactPersonDto dto = new CreateContactPersonDto(new NameDto("Tom", "Hanks"), "112", "112", "test@gmail.com", addressMapper.toDto(address));
        //when
        ContactPerson result = contactPersonMapper.toEntity(dto, address.getPostalCode());
        //then
        assertEquals(expected, result);
    }

    @Test
    void toDto() {
        //given
        Address address = new Address("Street", "1", new PostalCode(1000, "Brussel"));
        ContactPerson contactPerson = new ContactPerson(new Name("Tom", "Hanks"), "112", "112", "test@gmail.com", address);
        ContactPersonDto expected = new ContactPersonDto.Builder()
                .withId(contactPerson.getId())
                .withTelephoneNumber("112")
                .withMobilePhoneNumber("112")
                .withEmail("test@gmail.com")
                .withAddressDto(addressMapper.toDto(address))
                .withName(new NameDto("Tom", "Hanks"))
                .build();
        //when
        ContactPersonDto result = contactPersonMapper.toDto(contactPerson);
        //then
        assertEquals(expected, result);
    }
}