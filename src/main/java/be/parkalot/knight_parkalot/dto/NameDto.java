package be.parkalot.knight_parkalot.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameDto nameDto = (NameDto) o;
        return Objects.equals(firstName, nameDto.firstName) && Objects.equals(lastName, nameDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
