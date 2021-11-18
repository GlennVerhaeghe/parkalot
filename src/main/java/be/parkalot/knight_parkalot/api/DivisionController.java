package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.DivisionDto;
import be.parkalot.knight_parkalot.service.DivisionService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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


    @GetMapping(produces = "application/json")
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<DivisionDto> showOverviewOfAllDivisions() {
        return service.getAllDivisions();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public DivisionDto createNewDivision(@RequestBody CreateDivisionDto divisionDto) {
        return service.createNewDivision(divisionDto);
    }
}
