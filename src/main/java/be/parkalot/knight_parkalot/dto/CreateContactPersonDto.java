package be.parkalot.knight_parkalot.dto;

import java.util.Objects;

public class CreateContactPersonDto {

    private NameDto nameDto;
    private String mobilePhoneNumber;
    private String telephoneNumber;
    private String email;
    private AddressDto addressDto;

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
