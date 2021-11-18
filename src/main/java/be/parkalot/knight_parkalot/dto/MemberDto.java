package be.parkalot.knight_parkalot.dto;

import java.time.LocalDate;

public class MemberDto {

    private final NameDto nameDto;
    private final AddressDto addressDto;
    private final String telephoneNumber;
    private final String email;
    private final LicensePlateDto licensePlateDto;
    private final LocalDate registrationDate;
    private final MembershipLevelDto membershipLevelDto;

    public MemberDto(NameDto nameDto, AddressDto addressDto, String telephoneNumber, String email, LicensePlateDto licensePlateDto, LocalDate registrationDate, MembershipLevelDto membershipLevelDto) {
        this.nameDto = nameDto;
        this.addressDto = addressDto;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.licensePlateDto = licensePlateDto;
        this.registrationDate = registrationDate;
        this.membershipLevelDto = membershipLevelDto;
    }

    public NameDto getNameDto() {
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

    public MembershipLevelDto getMembershipLevelDto() {
        return membershipLevelDto;
    }
}
