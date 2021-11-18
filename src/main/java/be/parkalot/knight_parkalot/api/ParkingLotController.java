package be.parkalot.knight_parkalot.api;


import be.parkalot.knight_parkalot.dto.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.ParkingLotDto;
import be.parkalot.knight_parkalot.service.ParkingLotService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public ParkingLotDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        logger.info("createParkingLot() called");
        return parkingLotService.createNewParkingLot(createParkingLotDto);
    }

}
