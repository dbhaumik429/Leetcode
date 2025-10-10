package creditlimitdecrease;

import java.util.List;
import java.util.Objects;

/**
 * Coordinates the application of business rules and CRM integration.
 */
public final class CreditLimitDecreaseService {
    private final List<CreditLimitRule> rules;
    private final CrmCaseFactory crmCaseFactory;
    private final CrmClient crmClient;

    public CreditLimitDecreaseService(List<CreditLimitRule> rules, CrmCaseFactory crmCaseFactory, CrmClient crmClient) {
        this.rules = List.copyOf(Objects.requireNonNull(rules, "rules"));
        this.crmCaseFactory = Objects.requireNonNull(crmCaseFactory, "crmCaseFactory");
        this.crmClient = Objects.requireNonNull(crmClient, "crmClient");
    }

    public CreditLimitDecision process(CreditLimitDecreaseRequest request) {
        Objects.requireNonNull(request, "request");

        CreditLimitDecision decision = new CreditLimitDecision();
        decision.setStatus(CreditLimitDecision.DecisionStatus.APPROVED);

        for (CreditLimitRule rule : rules) {
            rule.apply(request, decision);
        }

        CrmCase crmCase = crmCaseFactory.createCase(request, decision);
        crmClient.createCase(crmCase);

        return decision;
    }
}
