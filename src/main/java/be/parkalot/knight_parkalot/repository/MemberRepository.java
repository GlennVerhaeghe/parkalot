package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

    Member findByEmail(String email);
    Member findByLicensePlate(LicensePlate licensePlate);
}
