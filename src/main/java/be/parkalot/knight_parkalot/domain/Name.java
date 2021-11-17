package be.parkalot.knight_parkalot.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

    private String firstName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
