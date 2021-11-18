package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostalCodeRepository extends JpaRepository<PostalCode, Integer> {
}
