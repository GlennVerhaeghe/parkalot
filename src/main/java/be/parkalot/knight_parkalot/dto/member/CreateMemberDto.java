package be.parkalot.knight_parkalot.dto.member;

import be.parkalot.knight_parkalot.dto.NameDto;
import be.parkalot.knight_parkalot.dto.address.AddressDto;

public class CreateMemberDto {

    private final NameDto nameDto;
    private final AddressDto addressDto;
    private final String telephoneNumber;
    private final String email;
    private final LicensePlateDto licensePlateDto;
    private final int membershipLevelID;

    public CreateMemberDto(NameDto nameDto, AddressDto addressDto, String telephoneNumber, String email, LicensePlateDto licensePlateDto, int membershipLevelID) {
        this.nameDto = nameDto;
        this.addressDto = addressDto;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.licensePlateDto = licensePlateDto;
        this.membershipLevelID = membershipLevelID;
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


    public int getMembershipLevelID() {
        return membershipLevelID;
    }
}
