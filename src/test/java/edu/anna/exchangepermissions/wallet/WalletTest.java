package edu.anna.exchangepermissions.wallet;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class WalletTest {

    private ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    private Logger logger = (Logger) LoggerFactory.getLogger(Wallet.class);

    @BeforeEach
    void setUp() {
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        listAppender.stop();
    }

    @Test
    void shouldNotLogIfChargeValueIsZero() {
        Wallet wallet = new Wallet();
        wallet.charge(new Charge(new BigDecimal("0"), "EUR", "A concept"));

        assertThat(listAppender.list.size(), equalTo(0));
    }

    @Test
    void shouldLogIfChargeValueIsMoreThanZero() {
        Wallet wallet = new Wallet();
        wallet.charge(new Charge(new BigDecimal("5"), "EUR", "A concept"));

        assertThat(listAppender.list.size(), equalTo(1));
    }
}