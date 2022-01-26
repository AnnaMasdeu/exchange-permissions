package edu.anna.exchangepermissions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RateId implements Serializable {

    private String exchangeId;
    private String level;

    private RateId() {

    }

    public RateId(String exchangeId, String level) {
        this.exchangeId = exchangeId;
        this.level = level;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateId that = (RateId) o;
        return Objects.equals(exchangeId, that.exchangeId) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeId, level);
    }
}
