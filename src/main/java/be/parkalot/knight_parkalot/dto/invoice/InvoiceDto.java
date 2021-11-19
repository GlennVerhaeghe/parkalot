package be.parkalot.knight_parkalot.dto.invoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDto {

    private final int id;
    private final List<InvoiceItemDto> invoiceItems;
    private final LocalDate creationDate;
    private final LocalDate expirationDate;
    private final boolean closed;
    private final LocalDate dateOfPayment;
    private final double totalPrice;

    public InvoiceDto(Builder builder) {
        id = builder.id;
        invoiceItems = builder.invoiceItems;
        creationDate = builder.creationDate;
        expirationDate = builder.expirationDate;
        closed = builder.closed;
        dateOfPayment = builder.dateOfPayment;
        totalPrice = builder.totalPrice;
    }

    public int getId() {
        return id;
    }

    public List<InvoiceItemDto> getInvoiceItems() {
        return invoiceItems;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static class Builder {
        private int id;
        private List<InvoiceItemDto> invoiceItems;
        private LocalDate creationDate;
        private LocalDate expirationDate;
        private boolean closed;
        private LocalDate dateOfPayment;
        private double totalPrice;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withInvoiceItems(List<InvoiceItemDto> invoiceItems) {
            this.invoiceItems = invoiceItems;
            return this;
        }

        public Builder withCreationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withExpirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder withClosed(boolean closed) {
            this.closed = closed;
            return this;
        }

        public Builder withDateOfPayment(LocalDate dateOfPayment) {
            this.dateOfPayment = dateOfPayment;
            return this;
        }

        public Builder withTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public InvoiceDto build() {
            return new InvoiceDto(this);
        }
    }
}
