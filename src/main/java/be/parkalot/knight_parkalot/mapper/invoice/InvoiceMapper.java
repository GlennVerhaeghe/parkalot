package be.parkalot.knight_parkalot.mapper.invoice;

import be.parkalot.knight_parkalot.domain.Invoice;
import be.parkalot.knight_parkalot.dto.invoice.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    private final InvoiceItemMapper invoiceItemMapper;

    @Autowired
    public InvoiceMapper(InvoiceItemMapper invoiceItemMapper) {
        this.invoiceItemMapper = invoiceItemMapper;
    }

    public InvoiceDto toDto(Invoice invoice) {
        return new InvoiceDto.Builder()
                .withId(invoice.getId())
                .withCreationDate(invoice.getCreationDate())
                .withClosed(invoice.isClosed())
                .withExpirationDate(invoice.getExpirationDate())
                .withInvoiceItems(invoice.getInvoiceItems().stream().map(invoiceItemMapper::toDto).toList())
                .withDateOfPayment(invoice.getDateOfPayment())
                .withTotalPrice(invoice.getTotalPrice())
                .build();
    }
}
