package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.DivisionDto;
import be.parkalot.knight_parkalot.service.DivisionService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private final DivisionService service;
    private final Logger logger = LoggerFactory.getLogger(DivisionController.class);

    @Autowired
    public DivisionController(DivisionService service) {
        this.service = service;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<DivisionDto> showOverviewOfAllDivisions() {
        logger.info("showOverviewOfAllDivisions() called.");
        return service.getAllDivisions();
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{divisionId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public DivisionDto getDivision(@PathVariable int divisionId){
        return service.getDivisionDtoById(divisionId);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public DivisionDto createNewDivision(@RequestBody CreateDivisionDto divisionDto) {
        logger.info("createNewDivision() called.");
        return service.createNewDivision(divisionDto);
    }
}
