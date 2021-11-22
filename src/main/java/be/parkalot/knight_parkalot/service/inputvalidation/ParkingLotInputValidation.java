package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.parkingLot.CreateParkingLotDto;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;

public class ParkingLotInputValidation {

    private final CreateParkingLotDto createParkingLotDto;
    private final AddressInputValidation addressInputValidation;
    private final ContactPersonInputValidation contactPersonInputValidation;

    public ParkingLotInputValidation(CreateParkingLotDto createParkingLotDto) {
        this.createParkingLotDto = createParkingLotDto;
        addressInputValidation = new AddressInputValidation(createParkingLotDto.getAddressDto());
        contactPersonInputValidation = new ContactPersonInputValidation(createParkingLotDto.getContactPersonDto());
    }

    public void validate() {

        addressInputValidation.validate();
        contactPersonInputValidation.validate();

        if (createParkingLotDto.getName() == null || createParkingLotDto.getName().isBlank()) {
            throw new MissingArgumentsException("Parking lot name has to be provided");
        }

        if (createParkingLotDto.getMaxCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity has to be positive");
        }

        if (createParkingLotDto.getPricePerHour() < 0) {
            throw new IllegalArgumentException("Price per hour has to be positive");
        }
    }
}
