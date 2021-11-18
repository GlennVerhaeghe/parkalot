package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.ContactPerson;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.ContactPersonDto;
import be.parkalot.knight_parkalot.dto.CreateContactPersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactPersonMapper {

    private final AddressMapper addressMapper;
    private final NameMapper nameMapper;

    @Autowired
    public ContactPersonMapper(AddressMapper addressMapper, NameMapper nameMapper) {
        this.addressMapper = addressMapper;
        this.nameMapper = nameMapper;
    }

    public ContactPerson toEntity(CreateContactPersonDto createContactPersonDto, PostalCode postalCode) {
        return new ContactPerson(
                nameMapper.toEntity(createContactPersonDto.getNameDto()),
                createContactPersonDto.getMobilePhoneNumber(),
                createContactPersonDto.getTelephoneNumber(),
                createContactPersonDto.getEmail(),
                addressMapper.toEntity(createContactPersonDto.getAddressDto(), postalCode)
        );
    }

    public ContactPersonDto toDto(ContactPerson contactPerson) {
        return new ContactPersonDto.Builder()
                .withId(contactPerson.getId())
                .withName(nameMapper.toDto(contactPerson.getName()))
                .withMobilePhoneNumber(contactPerson.getMobilePhoneNumber())
                .withTelephoneNumber(contactPerson.getTelephoneNumber())
                .withEmail(contactPerson.getEmail())
                .withAddressDto(addressMapper.toDto(contactPerson.getAddress()))
                .build();
    }
}
