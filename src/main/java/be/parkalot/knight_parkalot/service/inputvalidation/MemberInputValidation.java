package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.MemberDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberInputValidation {

    private final MemberDto memberDto;

    public MemberInputValidation(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public boolean validate() {
        if (!isEmailValid(memberDto.getEmail())) {
            //throw exception
        }

        if (memberDto.getNameDto() == null) {
            //throw exception
        }

        if (memberDto.getNameDto().getFirstName() == null) {
            //throw exception
        }

        if (memberDto.getNameDto().getLastName() == null) {
            //throw exception
        }

        return true;
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
