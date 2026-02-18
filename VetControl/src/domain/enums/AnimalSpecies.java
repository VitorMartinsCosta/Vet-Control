package domain.enums;

public enum AnimalSpecies {
    CAT ("Cat"),
    BIRD ("Bird"),
    RABBIT ("Rabbit"),
    RODENT ("Rodent"),
    REPTILE ("Reptile"),
    FISH ("Fish"),
    HORSE ("Horse"),
    CATTLE ("Cattle"),
    SWINE ("Swine"),
    SHEEP ("Sheep"),
    GOAT ("Goat"),
    OTHER ("Other");

    private final String description;

    private AnimalSpecies(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return description;
    }
}
