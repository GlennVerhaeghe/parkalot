package be.parkalot.knight_parkalot.service.inputvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddressValidation {
    private final String emailAddress;

    public EmailAddressValidation(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void validate() {
        if (emailAddress == null || emailAddress.isEmpty() || emailAddress.isBlank()) {
            throw new IllegalArgumentException("Email can not be empty.");
        }

        Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(emailAddress);

        if (!matcher.find()) {
            throw new IllegalArgumentException("This e-mail : " + emailAddress + " is not valid.");
        }
    }

}
