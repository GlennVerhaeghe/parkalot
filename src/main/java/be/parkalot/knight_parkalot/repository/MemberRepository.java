package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByEmail(String email);

    Member findByLicensePlate(LicensePlate licensePlate);
}
