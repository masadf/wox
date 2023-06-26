package md.koloritmarketplace.model.enums;

public enum UserStatus {
    ACTIVE("Active"),
    DISABLED("Disabled");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
