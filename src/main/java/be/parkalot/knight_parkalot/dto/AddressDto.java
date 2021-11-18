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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(streetName, that.streetName) && Objects.equals(houseNumber, that.houseNumber) && Objects.equals(postalCodeDto, that.postalCodeDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, houseNumber, postalCodeDto);
    }
}
