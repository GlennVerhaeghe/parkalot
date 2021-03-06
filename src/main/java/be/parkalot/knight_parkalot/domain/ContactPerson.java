package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_person")
public class ContactPerson {

    @Id
    @SequenceGenerator(name = "contact_person_seq", sequenceName = "CONTACT_PERSON_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_person_seq")
    private int id;

    @Embedded
    private Name name;

    @Column(name = "mobile_number")
    private String mobilePhoneNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public ContactPerson() {
    }

    public ContactPerson(Name name, String mobilePhoneNumber, String telephoneNumber, String email, Address address) {
        this.name = name;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPerson that = (ContactPerson) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) && Objects.equals(telephoneNumber, that.telephoneNumber) && Objects.equals(email, that.email) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mobilePhoneNumber, telephoneNumber, email, address);
    }
}
