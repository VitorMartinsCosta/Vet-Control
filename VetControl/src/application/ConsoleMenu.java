package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import domain.enums.AnimalSpecies;
import domain.model.Address;
import domain.model.Pet;
import domain.model.Tutor;
import service.TutorService;

public class ConsoleMenu {

    private final TutorService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(TutorService service) {
        this.service = service;
    }

    public void start() {
        int option;

        do {
            printMainMenu();
            option = readInt("Choose an option: ");

            try {
                switch (option) {
                    case 1 -> tutorMenu();
                    case 2 -> petMenu();
                    case 0 -> System.out.println("Exiting system...");
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (option != 0);
    }

    private void printMainMenu() {
        System.out.println("\n=== VETERINARY CLINIC SYSTEM ===");
        System.out.println("1 - Tutor Operations");
        System.out.println("2 - Pet Operations");
        System.out.println("0 - Exit");
    }

    // =======================
    // TUTOR MENU
    // =======================

    private void tutorMenu() {

        int option;

        do {
            System.out.println("\n--- Tutor Menu ---");
            System.out.println("1 - Create Tutor");
            System.out.println("2 - List Tutors");
            System.out.println("3 - Update Tutor");
            System.out.println("4 - Activate Tutor");
            System.out.println("5 - Deactivate Tutor");
            System.out.println("0 - Back");

            option = readInt("Choose: ");

            switch (option) {
                case 1 -> createTutor();
                case 2 -> listTutors();
                case 3 -> updateTutor();
                case 4 -> changeTutorStatus(true);
                case 5 -> changeTutorStatus(false);
            }

        } while (option != 0);
    }

    private void createTutor() {

        System.out.println("\n--- Create Tutor (0 to cancel) ---");

        String name = readStringOrCancel("Name: ");
        if (name == null) return;

        String cpf = readStringOrCancel("CPF: ");
        if (cpf == null) return;

        String phone = readStringOrCancel("Phone: ");
        if (phone == null) return;

        String email = readStringOrCancel("Email: ");
        if (email == null) return;

        System.out.println("Address:");

        String street = readStringOrCancel("Street: ");
        if (street == null) return;

        String number = readStringOrCancel("Number: ");
        if (number == null) return;

        String city = readStringOrCancel("City: ");
        if (city == null) return;

        String state = readStringOrCancel("State: ");
        if (state == null) return;

        String zip = readStringOrCancel("Zip: ");
        if (zip == null) return;

        String complement = readOptional("Complement: ");

        service.saveTutor(name, cpf, phone, email,
                new Address(street, number, city, state, zip, complement));

        System.out.println("Tutor created successfully.");
    }

    private void listTutors() {
        List<Tutor> tutors = service.getAllTutors();

        if (tutors.isEmpty()) {
            System.out.println("No tutors registered.");
            return;
        }

        tutors.forEach(t -> {
            System.out.println("\nCPF: " + t.getCpf());
            System.out.println("Name: " + t.getName());
            System.out.println("Status: " + t.getTutorStatus());
        });
    }

    private void updateTutor() {

        String cpf = readStringOrCancel("Tutor CPF: ");
        if (cpf == null) return;

        System.out.println("1 - Update Phone");
        System.out.println("2 - Update Email");
        System.out.println("3 - Update Address");

        int option = readInt("Choose: ");

        switch (option) {
            case 1 -> {
                String phone = readStringOrCancel("New phone: ");
                if (phone != null) service.updateTutorPhone(cpf, phone);
            }
            case 2 -> {
                String email = readStringOrCancel("New email: ");
                if (email != null) service.updateTutorEmail(cpf, email);
            }
            case 3 -> {
                System.out.println("New Address:");
                String street = readStringOrCancel("Street: ");
                if (street == null) return;
                String number = readStringOrCancel("Number: ");
                if (number == null) return;
                String city = readStringOrCancel("City: ");
                if (city == null) return;
                String state = readStringOrCancel("State: ");
                if (state == null) return;
                String zip = readStringOrCancel("Zip: ");
                if (zip == null) return;
                String complement = readOptional("Complement: ");

                service.updateTutorAddress(cpf,
                        new Address(street, number, city, state, zip, complement));
            }
        }

        System.out.println("Tutor updated.");
    }

    private void changeTutorStatus(boolean activate) {

        String cpf = readStringOrCancel("Tutor CPF: ");
        if (cpf == null) return;

        if (activate) service.activateTutor(cpf);
        else service.deactivateTutor(cpf);

        System.out.println("Status updated.");
    }

    // =======================
    // PET MENU
    // =======================

    private void petMenu() {

        int option;

        do {
            System.out.println("\n--- Pet Menu ---");
            System.out.println("1 - Register Pet");
            System.out.println("2 - List Pets by Tutor");
            System.out.println("3 - Mark Pet as Deceased");
            System.out.println("4 - Transfer Pet");
            System.out.println("0 - Back");

            option = readInt("Choose: ");

            switch (option) {
                case 1 -> registerPet();
                case 2 -> listPets();
                case 3 -> markPetDeceased();
                case 4 -> transferPet();
            }

        } while (option != 0);
    }

    private void registerPet() {

        String cpf = readStringOrCancel("Tutor CPF: ");
        if (cpf == null) return;

        String name = readStringOrCancel("Pet Name: ");
        if (name == null) return;

        String breed = readStringOrCancel("Breed: ");
        if (breed == null) return;

        double weight = readDouble("Weight: ");

        int year = readInt("Birth year: ");
        int month = readInt("Birth month: ");
        int day = readInt("Birth day: ");

        AnimalSpecies species =
                AnimalSpecies.valueOf(readStringOrCancel("Species: ").toUpperCase());

        service.registerPet(name, species, breed,
                LocalDate.of(year, month, day),
                weight, cpf);

        System.out.println("Pet registered.");
    }

    private void listPets() {

        String cpf = readStringOrCancel("Tutor CPF: ");
        if (cpf == null) return;

        List<Pet> pets = service.getPetsByTutorCpf(cpf);

        if (pets.isEmpty()) {
            System.out.println("No pets registered.");
            return;
        }

        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            System.out.println((i + 1) + " - " + p.getName() + " | Status: " + p.getPetStatus());
        }
    }

    private void markPetDeceased() {

        String cpf = readStringOrCancel("Tutor CPF: ");
        if (cpf == null) return;

        List<Pet> pets = service.getPetsByTutorCpf(cpf);
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }

        listPets();

        int index = readInt("Select pet number: ") - 1;

        if (index < 0 || index >= pets.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        service.markPetAsDeceased(cpf, pets.get(index).getId());

        System.out.println("Pet marked as deceased.");
    }

    private void transferPet() {

        String cpfFrom = readStringOrCancel("Source Tutor CPF: ");
        if (cpfFrom == null) return;

        List<Pet> pets = service.getPetsByTutorCpf(cpfFrom);
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }

        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + " - " + pets.get(i).getName());
        }

        int index = readInt("Select pet number: ") - 1;

        if (index < 0 || index >= pets.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String cpfTo = readStringOrCancel("Destination Tutor CPF: ");
        if (cpfTo == null) return;

        service.transferPet(cpfFrom, cpfTo, pets.get(index).getId());

        System.out.println("Pet transferred.");
    }

    // =======================
    // INPUT HELPERS
    // =======================

    private String readStringOrCancel(String msg) {
        System.out.print(msg);
        String input = scanner.nextLine();
        return input.equals("0") ? null : input;
    }

    private String readOptional(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private double readDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(scanner.nextLine());
    }
}