package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.MembershipLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipLevelRepository extends JpaRepository<MembershipLevel, Integer> {

}
