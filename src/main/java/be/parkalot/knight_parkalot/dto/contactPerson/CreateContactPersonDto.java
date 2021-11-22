package be.parkalot.knight_parkalot.dto.contactPerson;

import be.parkalot.knight_parkalot.dto.NameDto;
import be.parkalot.knight_parkalot.dto.address.AddressDto;

public class CreateContactPersonDto {

    private final NameDto nameDto;
    private final String mobilePhoneNumber;
    private final String telephoneNumber;
    private final String email;
    private final AddressDto addressDto;

    public CreateContactPersonDto(NameDto nameDto, String mobilePhoneNumber, String telephoneNumber, String email, AddressDto addressDto) {
        this.nameDto = nameDto;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.addressDto = addressDto;
    }

    public NameDto getNameDto() {
        return nameDto;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }
}
