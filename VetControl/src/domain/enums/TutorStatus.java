package domain.enums;

public enum TutorStatus {
    ACTIVE ("Active"),
    INACTIVE ("Inactive");

    private final String description;

    private TutorStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
