package creditlimitdecrease;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Small demo to illustrate the refactored design.
 */
public final class CreditLimitDecreaseExample {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("credit-limit-decrease");
        CreditLimitDecreaseFacade facade = CreditLimitDecreaseFacade.createDefault(logger);

        Account account = new Account("12345", LocalDate.now().minusMonths(4), true);
        CreditLimitDecreaseRequest request = new CreditLimitDecreaseRequest(account, 5000, 3000);
        CreditLimitDecision decision = facade.process(request);

        logger.info(() -> "Decision status: " + decision.getStatus());
        logger.info(() -> "Decision attributes: " + decision.getAttributes());
    }
}
