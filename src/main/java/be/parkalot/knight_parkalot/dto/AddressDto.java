package be.parkalot.knight_parkalot.dto;

import be.parkalot.knight_parkalot.domain.PostalCode;

public class AddressDto {
    private final String streetName;
    private final String houseNumber;
    private final PostalCode postalCode;

    public AddressDto(String streetName, String houseNumber, PostalCode postalCode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }
}
