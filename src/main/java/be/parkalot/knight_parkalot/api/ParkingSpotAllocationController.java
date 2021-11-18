package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.dto.CreateParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.dto.ParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.service.ParkingSpotAllocationService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingSpotAllocations")
public class ParkingSpotAllocationController {

    private ParkingSpotAllocationService parkingSpotAllocationService;
    private Logger logger = LoggerFactory.getLogger(ParkingSpotAllocationController.class);

    @Autowired
    public ParkingSpotAllocationController(ParkingSpotAllocationService parkingSpotAllocationService) {
        this.parkingSpotAllocationService = parkingSpotAllocationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingSpotAllocationDto startAllocating(@RequestBody CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        logger.info("startAllocating() called");
        return parkingSpotAllocationService.startAllocating(createParkingSpotAllocationDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<ParkingSpotAllocationDto> getAllParkingAllocations(@RequestParam(required = false) int limit,
                                                                   @RequestParam(required = false) String status,
                                                                   @RequestParam(required = false) boolean descending) {
        return parkingSpotAllocationService.getAll(limit, status, descending);
    }

}
