package creditlimitdecrease;

/**
 * Abstraction for CRM integrations.
 */
public interface CrmClient {
    void createCase(CrmCase crmCase);
}
