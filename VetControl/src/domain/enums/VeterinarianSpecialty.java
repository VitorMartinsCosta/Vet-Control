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

    public static void showOptions(){
        System.out.println("Available specialty: ");
        for (int i = 0; i < values().length; i++) {
            System.out.println((i + 1) + ", " + values()[i].getDescription());
        }
    }

    public static VeterinarianSpecialty getForNumber(int number){
        if(number < 1 || number > values().length){
            throw new IllegalArgumentException(
                "Number " + number + " out of valid range [1, " + values().length + "]"
            );
        }
        return values()[number - 1];
    }
}
