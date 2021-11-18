package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingLotCategory;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.ParkingLotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final ContactPersonMapper contactPersonMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public ParkingLotMapper(ContactPersonMapper contactPersonMapper, AddressMapper addressMapper) {
        this.contactPersonMapper = contactPersonMapper;
        this.addressMapper = addressMapper;
    }

    public ParkingLot toEntity(CreateParkingLotDto createParkingLotDto, PostalCode postalCodeContact, PostalCode postalCodeParkingLot, ParkingLotCategory parkingLotCategory, Division division) {
        return new ParkingLot(
                createParkingLotDto.getName(),
                createParkingLotDto.getMaxCapacity(),
                contactPersonMapper.toEntity(createParkingLotDto.getContactPersonDto(), postalCodeContact),
                addressMapper.toEntity(createParkingLotDto.getAddressDto(), postalCodeParkingLot),
                createParkingLotDto.getPricePerHour(),
                parkingLotCategory,
                division
        );
    }

    public ParkingLotDto toDto(ParkingLot parkingLot) {
        return new ParkingLotDto.Builder()
                .withId(parkingLot.getId())
                .withName(parkingLot.getName())
                .withMaxCapacity(parkingLot.getMaxCapacity())
                .withContactPersonDto(contactPersonMapper.toDto(parkingLot.getContactPerson()))
                .withPricePerHour(parkingLot.getPricePerHour())
                .withAddressDto(addressMapper.toDto(parkingLot.getAddress()))
                .withDivisionId(parkingLot.getDivision().getId())
                .withParkingLotCategoryId(parkingLot.getParkingLotCategory().getId())
                .build();
    }
}
