package edu.anna.exchangepermissions;

public class UserPermissionId {

    private String accountId;
    private String exchangeId;

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
}
