package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.member.CreateMemberDto;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;

public class MemberInputValidation {

    private final CreateMemberDto createMemberDto;
    private final AddressInputValidation addressInputValidation;
    private final EmailAddressValidation emailAddressValidation;
    private final NameInputValidation nameInputValidation;

    public MemberInputValidation(CreateMemberDto createMemberDto) {
        this.createMemberDto = createMemberDto;
        addressInputValidation = new AddressInputValidation(createMemberDto.getAddressDto());
        emailAddressValidation = new EmailAddressValidation(createMemberDto.getEmail());
        nameInputValidation = new NameInputValidation(createMemberDto.getNameDto());
    }

    public void validate() {

        if (createMemberDto.getTelephoneNumber() == null) {
            throw new MissingArgumentsException("City has to be provided");
        }
        if (createMemberDto.getLicensePlateDto() == null) {
            throw new MissingArgumentsException("License plate has to be provided");
        }
        if (createMemberDto.getLicensePlateDto().getNumber() == null) {
            throw new MissingArgumentsException("License plate number has to be provided");
        }
        if (createMemberDto.getLicensePlateDto().getCountryCode() == null) {
            throw new MissingArgumentsException("License plate Country code has to be provided");
        }
        nameInputValidation.validate();
        emailAddressValidation.validate();
        addressInputValidation.validate();
    }
}
