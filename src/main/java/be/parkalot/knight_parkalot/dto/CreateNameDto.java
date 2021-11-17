package be.parkalot.knight_parkalot.dto;

public class CreateNameDto {
    private String firstName;
    private String lastName;

    public CreateNameDto(String firstName, String lastName) {
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
