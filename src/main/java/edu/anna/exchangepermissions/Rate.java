package edu.anna.exchangepermissions;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Rate {

    @EmbeddedId
    private RateId rateId;

    private BigDecimal amount;

    private String currency;

    public RateId getRateId() {
        return rateId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
