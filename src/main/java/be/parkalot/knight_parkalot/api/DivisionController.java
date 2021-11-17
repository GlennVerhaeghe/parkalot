package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private final DivisionService service;

    @Autowired
    public DivisionController(DivisionService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Division createNewDivision(@RequestBody CreateDivisionDto divisionDto) {
        return service.createNewDivision(divisionDto);
    }

}
