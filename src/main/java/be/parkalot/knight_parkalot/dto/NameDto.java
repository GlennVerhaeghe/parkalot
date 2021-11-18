package be.parkalot.knight_parkalot.dto;

public class NameDto {
    private String firstName;
    private String lastName;

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
