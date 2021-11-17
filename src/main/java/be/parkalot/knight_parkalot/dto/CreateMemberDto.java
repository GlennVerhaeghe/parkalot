package be.parkalot.knight_parkalot.dto;

import java.time.LocalDate;

public class CreateMemberDto {

    private final CreateNameDto nameDto;
    private final AddressDto addressDto;
    private final String telephoneNumber;
    private final String email;
    private final LicensePlateDto licensePlateDto;
    private final LocalDate registrationDate;
    private final int membershipLevelID;

    public CreateMemberDto(CreateNameDto nameDto, AddressDto addressDto, String telephoneNumber, String email, LicensePlateDto licensePlateDto, int membershipLevelID) {
        this.nameDto = nameDto;
        this.addressDto = addressDto;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.licensePlateDto = licensePlateDto;
        this.registrationDate = LocalDate.now();
        this.membershipLevelID = membershipLevelID;
    }

    public CreateNameDto getNameDto() {
        return nameDto;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LicensePlateDto getLicensePlateDto() {
        return licensePlateDto;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public int getMembershipLevelID() {
        return membershipLevelID;
    }
}
