package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import domain.enums.AppointmentStatus;
import domain.enums.AppointmentType;

public class Appointment {
private Integer id;
private Pet pet;
private Tutor tutor;
private Veterinarian Veterinarian;

private LocalDate date;
private LocalTime time;

private AppointmentType appointmentType;
private AppointmentStatus appointmentStatus;

private String reason;
private String notes;

private double price;

public Appointment(Integer id, Pet pet, Tutor tutor, Veterinarian Veterinarian, LocalDate date, LocalTime time,
        AppointmentType appointmentType, AppointmentStatus appointmentStatus, String reason, String notes,
        double price) {
    this.id = id;
    this.pet = pet;
    this.tutor = tutor;
    this.Veterinarian = Veterinarian;
    this.date = date;
    this.time = time;
    this.appointmentType = appointmentType;
    this.appointmentStatus = appointmentStatus;
    this.reason = reason;
    this.notes = notes;
    this.price = price;
}

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public Pet getPet() {
    return pet;
}

public void setPet(Pet pet) {
    this.pet = pet;
}

public Tutor getTutor() {
    return tutor;
}

public void setTutor(Tutor tutor) {
    this.tutor = tutor;
}

public Veterinarian getVeterinarian() {
    return Veterinarian;
}

public void setVeterinarian(Veterinarian Veterinarian) {
    this.Veterinarian = Veterinarian;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public LocalTime getTime() {
    return time;
}

public void setTime(LocalTime time) {
    this.time = time;
}

public AppointmentType getAppointmentType() {
    return appointmentType;
}

public void setAppointmentType(AppointmentType appointmentType) {
    this.appointmentType = appointmentType;
}

public AppointmentStatus getAppointmentStatus() {
    return appointmentStatus;
}

public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
    this.appointmentStatus = appointmentStatus;
}

public String getReason() {
    return reason;
}

public void setReason(String reason) {
    this.reason = reason;
}

public String getNotes() {
    return notes;
}

public void setNotes(String notes) {
    this.notes = notes;
}

public double getPrice() {
    return price;
}

public void setPrice(double price) {
    this.price = price;
}

}
