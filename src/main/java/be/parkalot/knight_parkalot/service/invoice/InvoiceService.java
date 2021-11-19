package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.dto.invoice.InvoiceDto;
import be.parkalot.knight_parkalot.mapper.invoice.InvoiceMapper;
import be.parkalot.knight_parkalot.repository.InvoiceRepository;
import be.parkalot.knight_parkalot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemService invoiceItemService;
    private final InvoiceMapper invoiceMapper;
    private final MemberService memberService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceItemService invoiceItemService, InvoiceMapper invoiceMapper, MemberService memberService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemService = invoiceItemService;
        this.invoiceMapper = invoiceMapper;
        this.memberService = memberService;
    }

    public InvoiceDto getInvoiceForMemberId(int memberId) {
        Member member = memberService.getMemberById(memberId);
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItemsByMember(member);

        return null;
    }

    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceMapper::toDto).collect(Collectors.toList());
    }

}
