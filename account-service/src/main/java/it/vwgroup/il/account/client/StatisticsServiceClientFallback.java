package it.vwgroup.il.account.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.vwgroup.il.account.domain.Account;

/**
 * @author cdov
 */
@Component
public class StatisticsServiceClientFallback implements StatisticsServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceClientFallback.class);
    @Override
    public void updateStatistics(String accountName, Account account) {
        LOGGER.error("Error during update statistics for account: {}", accountName);
    }
}
