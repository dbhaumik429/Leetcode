package creditlimitdecrease;

/**
 * Default policy that sets attributes for new vs mature accounts.
 */
public final class DefaultAccountAgePolicy implements AccountAgeRule.AccountAgePolicy {
    private final String accountAgeAttribute;

    public DefaultAccountAgePolicy(String accountAgeAttribute) {
        this.accountAgeAttribute = accountAgeAttribute;
    }

    @Override
    public void applyForNewAccount(CreditLimitDecreaseRequest request, CreditLimitDecision decision) {
        decision.setAttribute(accountAgeAttribute, "UNDER_SIX_MONTHS");
        decision.setStatus(CreditLimitDecision.DecisionStatus.MANUAL_REVIEW);
        decision.setComments("Account younger than six months. Manual review required.");
    }

    @Override
    public void applyForMatureAccount(CreditLimitDecreaseRequest request, CreditLimitDecision decision) {
        decision.setAttribute(accountAgeAttribute, "OVER_SIX_MONTHS");
        if (decision.getStatus() == CreditLimitDecision.DecisionStatus.PENDING) {
            decision.setStatus(CreditLimitDecision.DecisionStatus.APPROVED);
        }
        if (decision.getComments().isEmpty()) {
            decision.setComments("Account older than six months. Auto-approved.");
        }
    }
}
