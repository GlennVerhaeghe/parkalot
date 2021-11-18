package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;

public class DivisionInputValidation {

    private final CreateDivisionDto createDivisionDto;
    private final NameInputValidation nameInputValidation;

    public DivisionInputValidation(CreateDivisionDto createDivisionDto) {
        this.createDivisionDto = createDivisionDto;
        this.nameInputValidation = new NameInputValidation(createDivisionDto.getDirectorName());
    }

    public void validate() {

        nameInputValidation.validate();

    }
}
