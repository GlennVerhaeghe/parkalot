package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateMemberDto;
import be.parkalot.knight_parkalot.exceptions.MissingMemberArgumentsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberInputValidation {

    private final CreateMemberDto createMemberDto;

    public MemberInputValidation(CreateMemberDto createMemberDto) {
        this.createMemberDto = createMemberDto;
    }

    public void validate() {
        if (!isEmailValid(createMemberDto.getEmail())) {
            throw new MissingMemberArgumentsException("Email has to be valid");
        }

        if (createMemberDto.getNameDto() == null) {
            throw new MissingMemberArgumentsException("User name has to be provided");
        }

        if (createMemberDto.getNameDto().getFirstName() == null) {
            throw new MissingMemberArgumentsException("First name has to be provided");
        }

        if (createMemberDto.getNameDto().getLastName() == null) {
            throw new MissingMemberArgumentsException("Last name has to be provided");
        }

        if (createMemberDto.getAddressDto() == null) {
            throw new MissingMemberArgumentsException("Address has to be provided");
        }

        if (createMemberDto.getAddressDto().getStreetName() == null) {
            throw new MissingMemberArgumentsException("Street name has to be provided");
        }
        if (createMemberDto.getAddressDto().getHouseNumber() == null) {
            throw new MissingMemberArgumentsException("House number has to be provided");
        }
        if (createMemberDto.getAddressDto().getPostalCodeDto() == null) {
            throw new MissingMemberArgumentsException("Postal Code and city have to be provided");
        }
        if (createMemberDto.getAddressDto().getPostalCodeDto().getCode() == 0) {
            throw new MissingMemberArgumentsException("Postal code has to be provided");
        }
        if (createMemberDto.getAddressDto().getPostalCodeDto().getCity() == null) {
            throw new MissingMemberArgumentsException("City has to be provided");
        }

        if (createMemberDto.getTelephoneNumber() == null) {
            throw new MissingMemberArgumentsException("City has to be provided");
        }
    }

    private boolean isEmailValid(String email) {
        if (email == null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email can not be empty.");
        }

        Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(email);

        if (matcher.find()) {
            return true;
        } else {
            throw new IllegalArgumentException("This e-mail : " + email + " is not valid.");
        }
    }
}
