package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.*;
import be.parkalot.knight_parkalot.dto.parkingLot.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.parkingLot.ParkingLotDetailsDto;
import be.parkalot.knight_parkalot.dto.parkingLot.ParkingLotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final ContactPersonMapper contactPersonMapper;
    private final AddressMapper addressMapper;
    private final DivisionMapper divisionMapper;

    @Autowired
    public ParkingLotMapper(ContactPersonMapper contactPersonMapper, AddressMapper addressMapper, DivisionMapper divisionMapper) {
        this.contactPersonMapper = contactPersonMapper;
        this.addressMapper = addressMapper;
        this.divisionMapper = divisionMapper;
    }

    public ParkingLot toEntity(CreateParkingLotDto createParkingLotDto, ContactPerson contactPerson, PostalCode postalCodeParkingLot, ParkingLotCategory parkingLotCategory, Division division) {
        return new ParkingLot(
                createParkingLotDto.getName(),
                createParkingLotDto.getMaxCapacity(),
                contactPerson,
                addressMapper.toEntity(createParkingLotDto.getAddressDto(), postalCodeParkingLot),
                createParkingLotDto.getPricePerHour(),
                parkingLotCategory,
                division
        );
    }

    public ParkingLotDetailsDto toDetailsDto(ParkingLot parkingLot) {
        return new ParkingLotDetailsDto.Builder()
                .withId(parkingLot.getId())
                .withName(parkingLot.getName())
                .withMaxCapacity(parkingLot.getMaxCapacity())
                .withContactPersonDto(contactPersonMapper.toDto(parkingLot.getContactPerson()))
                .withPricePerHour(parkingLot.getPricePerHour())
                .withAddressDto(addressMapper.toDto(parkingLot.getAddress()))
                .withDivision(divisionMapper.toDto(parkingLot.getDivision()))
                .withParkingLotCategoryName(parkingLot.getParkingLotCategory().getName())
                .build();
    }

    public ParkingLotDto toDto(ParkingLot parkingLot) {
        return new ParkingLotDto.Builder()
                .withId(parkingLot.getId())
                .withName(parkingLot.getName())
                .withMaxCapacity(parkingLot.getMaxCapacity())
                .withContactPersonEmail(parkingLot.getContactPerson().getEmail())
                .withContactPersonPhoneNumber(parkingLot.getContactPerson().getMobilePhoneNumber() != null ? parkingLot.getContactPerson().getMobilePhoneNumber() : parkingLot.getContactPerson().getTelephoneNumber())
                .build();
    }
}
