package edu.anna.exchangepermissions;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rate {

    @Id
    private Integer id;

    private String exchangeId;

    public Integer getId() {
        return id;
    }

    public String getExchangeId() {
        return exchangeId;
    }
}
