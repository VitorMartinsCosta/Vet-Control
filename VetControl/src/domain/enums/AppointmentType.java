package domain.enums;


public enum AppointmentType {
    CONSULTATION("Consultation"),
    RETURN("Return"), 
    EMERGENCY("Emergency");

    private final String description;

    AppointmentType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
