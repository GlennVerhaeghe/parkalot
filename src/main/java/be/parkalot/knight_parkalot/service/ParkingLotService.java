package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.dto.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.ParkingLotDto;
import be.parkalot.knight_parkalot.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    private final ParkingLotRepository repository;

    public ParkingLotService(ParkingLotRepository repository) {
        this.repository = repository;
    }


    public ParkingLotDto createNewParkingLot(CreateParkingLotDto parkingLotDto) {
        return null;
    }

}
