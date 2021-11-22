package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.address.AddressDto;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;

public class AddressInputValidation {
    private final AddressDto addressDto;

    public AddressInputValidation(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public void validate() {
        if (addressDto == null) {
            throw new MissingArgumentsException("Address has to be provided");
        }
        if (addressDto.getStreetName() == null || addressDto.getStreetName().isBlank()) {
            throw new MissingArgumentsException("Street name has to be provided");
        }
        if (addressDto.getHouseNumber() == null || addressDto.getHouseNumber().isBlank()) {
            throw new MissingArgumentsException("House number has to be provided");
        }
        if (addressDto.getPostalCodeDto() == null) {
            throw new MissingArgumentsException("Postal Code and city have to be provided");
        }
        if (addressDto.getPostalCodeDto().getCode() == 0) {
            throw new MissingArgumentsException("Postal code has to be provided");
        }
        if (addressDto.getPostalCodeDto().getCity() == null || addressDto.getPostalCodeDto().getCity().isBlank()) {
            throw new MissingArgumentsException("City has to be provided");
        }
    }
}
