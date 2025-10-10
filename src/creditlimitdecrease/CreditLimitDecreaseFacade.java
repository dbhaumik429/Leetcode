package creditlimitdecrease;

import java.time.Clock;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Facade to expose the service with sensible defaults.
 */
public final class CreditLimitDecreaseFacade {
    private final CreditLimitDecreaseService service;

    private CreditLimitDecreaseFacade(CreditLimitDecreaseService service) {
        this.service = service;
    }

    public CreditLimitDecision process(CreditLimitDecreaseRequest request) {
        return service.process(request);
    }

    public static CreditLimitDecreaseFacade createDefault(Logger logger) {
        Objects.requireNonNull(logger, "logger");

        AccountAgeRule.AccountAgePolicy agePolicy = new DefaultAccountAgePolicy("ACCOUNT_AGE_CATEGORY");
        CreditLimitRule ageRule = new AccountAgeRule(Clock.systemUTC(), agePolicy);
        CreditLimitRule vulnerabilityRule = new VulnerabilityRule("CUSTOMER_VULNERABILITY");

        CrmCaseFactory crmCaseFactory = new CrmCaseFactory("CREDIT_LIMIT_DECREASE");
        CrmClient crmClient = new LoggingCrmClient(logger);

        CreditLimitDecreaseService service = new CreditLimitDecreaseService(
                List.of(ageRule, vulnerabilityRule),
                crmCaseFactory,
                crmClient
        );

        return new CreditLimitDecreaseFacade(service);
    }
}
