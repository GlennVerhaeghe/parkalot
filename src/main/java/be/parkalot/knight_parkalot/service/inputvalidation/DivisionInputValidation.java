package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DivisionInputValidation {

    public void validate(CreateDivisionDto createDivisionDto) {

        if (createDivisionDto.getName() == null) {
            throw new IllegalArgumentException("Please fill the name");
        }

        if (createDivisionDto.getDirectorName() == null) {
            throw new IllegalArgumentException("Please fill the Director name");
        }

        if (createDivisionDto.getOldName() == null) {
            throw new IllegalArgumentException("Please fill the Old division name");
        }
    }
}
