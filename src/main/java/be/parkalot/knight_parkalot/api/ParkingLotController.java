package be.parkalot.knight_parkalot.api;


import be.parkalot.knight_parkalot.dto.parkingLot.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.parkingLot.ParkingLotDetailsDto;
import be.parkalot.knight_parkalot.dto.parkingLot.ParkingLotDto;
import be.parkalot.knight_parkalot.service.ParkingLotService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/parkinglots")
public class ParkingLotController {

    private final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);
    private final ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public ParkingLotDetailsDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        logger.info("createParkingLot() called");
        return parkingLotService.createNewParkingLot(createParkingLotDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<ParkingLotDto> getAllParkingLots() {
        logger.info("getAllParkingLots() called");
        return parkingLotService.getAllParkingLots();
    }

    @GetMapping(path = "/{parkingLotId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public ParkingLotDetailsDto getAParkingLot(@PathVariable int parkingLotId) {
        logger.info("getAParkingLot() called for id: " + parkingLotId);
        return parkingLotService.getParkingLotById(parkingLotId);
    }
}
