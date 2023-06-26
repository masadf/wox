package md.koloritmarketplace.model.enums;

public enum AccountRole {
    ADMIN("ADMIN"),
    CLIENT("CLIENT"),
    MANAGER("MANAGER");

    private final String role;

    AccountRole(String status) {
        this.role = status;
    }

    public String getValue() {
        return role;
    }
}
