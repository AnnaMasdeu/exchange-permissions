package edu.anna.exchangepermissions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserPermissionId implements Serializable {

    private String accountId;
    private String exchangeId;

    private UserPermissionId() {

    }

    public UserPermissionId(String accountId, String exchangeId) {
        this.accountId = accountId;
        this.exchangeId = exchangeId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionId that = (UserPermissionId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(exchangeId, that.exchangeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, exchangeId);
    }
}
