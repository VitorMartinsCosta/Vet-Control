package domain.enums;

public enum VeterinarianSpecialty {
    CLINICAL_GENERAL ("Clinical General"),
    DERMATOLOGY ("Dermatology"),
    CARDIOLOGY ("Cardiology"),
    NEUROLOGY ("Neurology"),
    ORTHOPEDICS ("Orthopedics"),
    ONCOLOGY ("Oncology"),
    OPHTHALMOLOGY ("Ophthamology"),
    ANESTHESIOLOGY ("Anesthesiology"),
    DIAGNOSTIC_IMAGING ("Diagnostic Imaging"),
    NUTRITION ("Nutrition"),
    BEHAVIOR ("Behavior"),
    DENTISTRY ("Dentistry"),
    EMERGENCY_AND_CRITICAL_CARE ("Emergency and Critical Care");

    private final String description;
    
    private VeterinarianSpecialty (String description){
        this.description = description; 
    }

    public String getDescription() {
        return description;
    }

}
