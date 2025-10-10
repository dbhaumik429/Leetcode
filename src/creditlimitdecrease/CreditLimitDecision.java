package creditlimitdecrease;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Describes the outcome of processing the credit limit decrease rules.
 */
public final class CreditLimitDecision {
    private final Map<String, String> attributes = new LinkedHashMap<>();
    private DecisionStatus status = DecisionStatus.PENDING;
    private String comments = "";

    public void setAttribute(String key, String value) {
        attributes.put(Objects.requireNonNull(key, "key"), Objects.requireNonNull(value, "value"));
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public void setStatus(DecisionStatus status) {
        this.status = Objects.requireNonNull(status, "status");
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = Objects.requireNonNull(comments, "comments");
    }

    public enum DecisionStatus {
        APPROVED,
        MANUAL_REVIEW,
        BLOCKED,
        PENDING
    }
}
