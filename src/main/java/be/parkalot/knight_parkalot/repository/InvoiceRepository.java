package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
