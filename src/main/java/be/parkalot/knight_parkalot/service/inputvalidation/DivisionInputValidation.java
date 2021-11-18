package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;

public class DivisionInputValidation {

    private final CreateDivisionDto createDivisionDto;
    private final NameInputValidation nameInputValidation;

    public DivisionInputValidation(CreateDivisionDto createDivisionDto) {
        this.createDivisionDto = createDivisionDto;
        this.nameInputValidation = new NameInputValidation(createDivisionDto.getDirectorName());
    }

    public void validate() {
        if (createDivisionDto.getName() == null || createDivisionDto.getName().isBlank()) {
            throw new MissingArgumentsException("Division name has to be provided.");
        }
        nameInputValidation.validate();
    }
}
