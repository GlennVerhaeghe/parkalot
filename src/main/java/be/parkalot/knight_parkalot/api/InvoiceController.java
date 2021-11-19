package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.dto.invoice.InvoiceDto;
import be.parkalot.knight_parkalot.service.invoice.InvoiceService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MEMBER)
    public InvoiceDto getInvoice(@PathVariable int memberId) {
        logger.info("getInvoice() called");
        return invoiceService.getInvoiceForMemberId(memberId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<InvoiceDto> getAllInvoices() {
        logger.info("getAllInvoices() called");
        return invoiceService.getAllInvoices();
    }

    @PutMapping(path = "{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public InvoiceDto closeInvoice(@PathVariable int invoiceId) {
        logger.info("closeInvoice() called");
        return invoiceService.closeInvoice(invoiceId);
    }
}
