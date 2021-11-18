package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateContactPersonDto;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;

public class ContactPersonInputValidation {

    private final CreateContactPersonDto createContactPersonDto;
    private final AddressInputValidation addressInputValidation;
    private final EmailAddressValidation emailAddressValidation;
    private final NameInputValidation nameInputValidation;

    public ContactPersonInputValidation(CreateContactPersonDto createContactPersonDto) {
        this.createContactPersonDto = createContactPersonDto;
        addressInputValidation = new AddressInputValidation(createContactPersonDto.getAddressDto());
        emailAddressValidation = new EmailAddressValidation(createContactPersonDto.getEmail());
        nameInputValidation = new NameInputValidation(createContactPersonDto.getNameDto());
    }

    public void validate() {
        addressInputValidation.validate();
        emailAddressValidation.validate();
        nameInputValidation.validate();

        if ((createContactPersonDto.getMobilePhoneNumber() == null || createContactPersonDto.getMobilePhoneNumber().isBlank())
                && (createContactPersonDto.getTelephoneNumber() == null || createContactPersonDto.getTelephoneNumber().isBlank())) {
            throw new MissingArgumentsException("Either Telephone number or Mobile number has to be provided.");
        }

    }

}
