package creditlimitdecrease;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Simple CRM client used for demonstration purposes.
 */
public final class LoggingCrmClient implements CrmClient {
    private final Logger logger;

    public LoggingCrmClient(Logger logger) {
        this.logger = Objects.requireNonNull(logger, "logger");
    }

    @Override
    public void createCase(CrmCase crmCase) {
        logger.info(() -> String.format(
                "Creating CRM case for account %s with type %s and attributes %s",
                crmCase.getAccountId(),
                crmCase.getCaseType(),
                crmCase.getAttributes()
        ));
    }
}
