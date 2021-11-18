package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.NameDto;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;

public class NameInputValidation {

    private final NameDto name;

    public NameInputValidation(NameDto name) {
        this.name = name;
    }

    public void validate() {
        if (name == null) {
            throw new MissingArgumentsException("Name is required");
        }
        if (name.getLastName() == null || name.getLastName().isBlank()) {
            throw new MissingArgumentsException("Last name is required");
        }
    }
}
