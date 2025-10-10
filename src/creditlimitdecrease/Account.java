package creditlimitdecrease;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the bank account affected by a credit limit decrease request.
 */
public final class Account {
    private final String accountId;
    private final LocalDate startDate;
    private final boolean vulnerableCustomer;

    public Account(String accountId, LocalDate startDate, boolean vulnerableCustomer) {
        this.accountId = Objects.requireNonNull(accountId, "accountId");
        this.startDate = Objects.requireNonNull(startDate, "startDate");
        this.vulnerableCustomer = vulnerableCustomer;
    }

    public String getAccountId() {
        return accountId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isVulnerableCustomer() {
        return vulnerableCustomer;
    }
}
