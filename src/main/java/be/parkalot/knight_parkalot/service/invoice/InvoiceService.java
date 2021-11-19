package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.mapper.invoice.InvoiceMapper;
import be.parkalot.knight_parkalot.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemService invoiceItemService;
    private final InvoiceMapper invoiceMapper;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceItemService invoiceItemService, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemService = invoiceItemService;
        this.invoiceMapper = invoiceMapper;
    }
}
