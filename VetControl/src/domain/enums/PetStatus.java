package domain.enums;

public enum PetStatus {
    ACTIVE("Active"),
    DECEASED("Deceased");

    private final String description;

    private PetStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}



