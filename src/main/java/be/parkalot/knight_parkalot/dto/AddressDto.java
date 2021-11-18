package be.parkalot.knight_parkalot.dto;


import java.util.Objects;

public class AddressDto {
    private final String streetName;
    private final String houseNumber;
    private final PostalCodeDto postalCodeDto;

    public AddressDto(String streetName, String houseNumber, PostalCodeDto postalCodeDto) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCodeDto = postalCodeDto;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public PostalCodeDto getPostalCodeDto() {
        return postalCodeDto;
    }
}
