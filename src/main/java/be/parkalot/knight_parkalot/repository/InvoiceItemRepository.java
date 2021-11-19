package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
}
