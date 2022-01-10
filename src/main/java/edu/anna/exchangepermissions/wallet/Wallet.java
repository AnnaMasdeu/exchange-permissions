package edu.anna.exchangepermissions.wallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Wallet {

    private static final Logger LOGGER = LoggerFactory.getLogger(Wallet.class);

    public void charge(Charge charge) {
        if (charge.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            LOGGER.info("Charging {}", charge);
        }
    }
}
