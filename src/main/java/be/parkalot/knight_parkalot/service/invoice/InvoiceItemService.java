package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.mapper.invoice.InvoiceItemMapper;
import be.parkalot.knight_parkalot.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceItemService {

    private final InvoiceItemRepository repository;
    private final InvoiceItemMapper mapper;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository repository, InvoiceItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
