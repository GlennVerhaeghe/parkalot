package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Integer> {

    boolean existsByEmail(String email);
    ContactPerson getByEmail(String email);
}
