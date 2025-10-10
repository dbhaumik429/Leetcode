package creditlimitdecrease;

import java.util.Objects;

/**
 * Contains the data necessary to process a credit limit decrease.
 */
public final class CreditLimitDecreaseRequest {
    private final Account account;
    private final int currentLimit;
    private final int proposedLimit;

    public CreditLimitDecreaseRequest(Account account, int currentLimit, int proposedLimit) {
        if (proposedLimit > currentLimit) {
            throw new IllegalArgumentException("Proposed limit must be less than or equal to current limit");
        }
        this.account = Objects.requireNonNull(account, "account");
        this.currentLimit = currentLimit;
        this.proposedLimit = proposedLimit;
    }

    public Account getAccount() {
        return account;
    }

    public int getCurrentLimit() {
        return currentLimit;
    }

    public int getProposedLimit() {
        return proposedLimit;
    }
}
