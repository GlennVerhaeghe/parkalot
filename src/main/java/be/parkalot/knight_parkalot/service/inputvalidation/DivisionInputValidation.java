package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;

public class DivisionInputValidation {

    public void validateDivisionInput(CreateDivisionDto createDivisionDto) {

        if (createDivisionDto.getName() == null || createDivisionDto.getName().isBlank()) {
            throw new IllegalArgumentException("Please fill the name");
        }

        if (createDivisionDto.getDirectorName() == null || createDivisionDto.getDirectorName().getLastName() == null
                || createDivisionDto.getDirectorName().getLastName().isBlank()) {
            throw new IllegalArgumentException("Please fill the Director name");
        }

    }
}
