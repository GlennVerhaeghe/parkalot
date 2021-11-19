package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.dto.invoice.InvoiceDto;
import be.parkalot.knight_parkalot.service.invoice.InvoiceService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MEMBER)
    public InvoiceDto getInvoice(@PathVariable int memberId) {
        return invoiceService.getInvoiceForMemberId(memberId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<InvoiceDto> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }
}
