package edu.anna.exchangepermissions.wallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Wallet {

    private static final Logger LOGGER = LoggerFactory.getLogger(Wallet.class);

    public void charge(Charge charge) {
        LOGGER.info("Charging {}", charge);
    }
}
