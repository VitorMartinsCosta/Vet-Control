package domain.enums;

public enum PetStatus {
    ACTIVE("Active"),
    DECEASED("Deceased"),
    TRANSFERRED_OUT("Transferred out");

    private final String description;

    private PetStatus(String description) {
        this.description = description;
    }

    public String getDesciption(){
        return description;
    }
}



