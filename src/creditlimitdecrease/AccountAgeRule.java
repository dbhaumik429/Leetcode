package creditlimitdecrease;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Applies different attributes depending on whether the account is older than six months.
 */
public final class AccountAgeRule implements CreditLimitRule {
    private static final int AGE_THRESHOLD_MONTHS = 6;

    private final Clock clock;
    private final AccountAgePolicy policy;

    public AccountAgeRule(Clock clock, AccountAgePolicy policy) {
        this.clock = Objects.requireNonNull(clock, "clock");
        this.policy = Objects.requireNonNull(policy, "policy");
    }

    @Override
    public void apply(CreditLimitDecreaseRequest request, CreditLimitDecision decision) {
        Account account = request.getAccount();
        LocalDate today = LocalDate.now(clock);
        Period accountAge = Period.between(account.getStartDate(), today);
        boolean isOlderThanThreshold = accountAge.toTotalMonths() >= AGE_THRESHOLD_MONTHS;

        if (isOlderThanThreshold) {
            policy.applyForMatureAccount(request, decision);
        } else {
            policy.applyForNewAccount(request, decision);
        }
    }

    /**
     * Defines the actions to take for new vs mature accounts.
     */
    public interface AccountAgePolicy {
        void applyForNewAccount(CreditLimitDecreaseRequest request, CreditLimitDecision decision);

        void applyForMatureAccount(CreditLimitDecreaseRequest request, CreditLimitDecision decision);
    }
}
