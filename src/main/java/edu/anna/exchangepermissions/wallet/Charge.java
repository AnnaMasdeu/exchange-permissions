package edu.anna.exchangepermissions.wallet;

import edu.anna.exchangepermissions.ViewPermission;

import java.math.BigDecimal;
import java.util.Objects;

public class Charge {

    private final BigDecimal amount;
    private final String currency;
    private final String concept;

    public Charge(BigDecimal amount, String currency, String concept) {
        this.amount = amount;
        this.currency = currency;
        this.concept = concept;
    }

    public static Charge permissionCharge(ViewPermission viewPermission) {
        switch(viewPermission) {
            case L1:
                return new Charge(new BigDecimal("5"), "EUR", "A concept");
            case L2:
                return new Charge(new BigDecimal("10"), "EUR", "A concept");
            default:
                return new Charge(new BigDecimal("0"), "EUR", "A concept");
        }
    }

    public Charge minus(Charge charge) {
        // TODO: check if currencies match
        return new Charge(amount.subtract(charge.getAmount()), currency, concept);
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
