package creditlimitdecrease;

/**
 * A single business rule that can mutate the {@link CreditLimitDecision}.
 */
public interface CreditLimitRule {
    void apply(CreditLimitDecreaseRequest request, CreditLimitDecision decision);
}
