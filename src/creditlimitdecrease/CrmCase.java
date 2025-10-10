package creditlimitdecrease;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the payload sent to CRM when creating a case.
 */
public final class CrmCase {
    private final String accountId;
    private final String caseType;
    private final Map<String, String> attributes;
    private final String comments;

    public CrmCase(String accountId, String caseType, Map<String, String> attributes, String comments) {
        this.accountId = Objects.requireNonNull(accountId, "accountId");
        this.caseType = Objects.requireNonNull(caseType, "caseType");
        this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(Objects.requireNonNull(attributes, "attributes")));
        this.comments = Objects.requireNonNull(comments, "comments");
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCaseType() {
        return caseType;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getComments() {
        return comments;
    }
}
