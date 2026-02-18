package domain.model;

import domain.enums.VeterinarianSpecialty;

public class Veterinarian {

    private Integer id;
    private String name;
    private String crmv;
    private VeterinarianSpecialty specialty;  // Formato: "UF-NUMERO"

    
    public Veterinarian() {
    }

    public Veterinarian(Integer id, String name, String crmv, VeterinarianSpecialty specialty) {
        this.id = id;
        this.name = name;
        this.crmv = crmv;
        this.specialty = specialty;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public VeterinarianSpecialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(VeterinarianSpecialty specialty) {
        this.specialty = specialty;
    }
    
}   
