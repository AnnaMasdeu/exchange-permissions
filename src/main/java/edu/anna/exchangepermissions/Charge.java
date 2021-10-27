package edu.anna.exchangepermissions;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class Charge {

    private final BigDecimal amount;
    private final String currency;
    private final String concept;

    private Charge(BigDecimal amount, String currency, String concept) {
        this.amount = amount;
        this.currency = currency;
        this.concept = concept;
    }

    public static Optional<Charge> permissionCharge(ViewPermission viewPermission) {
        switch(viewPermission) {
            case L1:
                return Optional.of(new Charge(new BigDecimal("5"), "EUR", "A concept"));
            case L2:
                return Optional.of(new Charge(new BigDecimal("10"), "EUR", "A concept"));
            default:
                return Optional.empty();
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getConcept() {
        return concept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charge charge = (Charge) o;
        return Objects.equals(amount, charge.amount) && Objects.equals(currency, charge.currency) && Objects.equals(concept, charge.concept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency, concept);
    }

    @Override
    public String toString() {
        return "Charge{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", concept='" + concept + '\'' +
                '}';
    }
}
