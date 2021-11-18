package be.parkalot.knight_parkalot.dto;

public class CreateContactPersonDto {

    private NameDto nameDto;
    private String mobilePhoneNumber;
    private String telephoneNumber;
    private String email;
    private AddressDto addressDto;

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
