package be.parkalot.knight_parkalot.dto;

public class NameDto {
    private final String firstName;
    private final String lastName;

    public NameDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
