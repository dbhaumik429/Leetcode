package creditlimitdecrease;

import java.util.Objects;

/**
 * Translates a decision into the CRM case representation.
 */
public final class CrmCaseFactory {
    private final String caseType;

    public CrmCaseFactory(String caseType) {
        this.caseType = Objects.requireNonNull(caseType, "caseType");
    }

    public CrmCase createCase(CreditLimitDecreaseRequest request, CreditLimitDecision decision) {
        return new CrmCase(
                request.getAccount().getAccountId(),
                caseType,
                decision.getAttributes(),
                decision.getComments()
        );
    }
}
