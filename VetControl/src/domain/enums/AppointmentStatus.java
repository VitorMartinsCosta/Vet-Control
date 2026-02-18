package domain.enums;


public enum AppointmentStatus {
    SCHEDULED ("Scheduled"),
    CONFIRMED ("Confirmed"),
    COMPLETED ("Completed"),
    CANCELLED ("Cancelled");

    private final String description;

    private AppointmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
