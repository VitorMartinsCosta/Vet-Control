package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import domain.enums.AnimalSpecies;
import domain.enums.TutorStatus;
import domain.model.Address;
import domain.model.Pet;
import domain.model.Tutor;
import service.TutorService;

public class ConsoleMenu {

    private final TutorService tutorService;
    private final Scanner scanner;

    public ConsoleMenu(TutorService tutorService) {
        this.tutorService = tutorService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        int option;

        do {
            printMenu();
            option = readInt("Choose an option: ");

            try {
                switch (option) {
                    case 1 -> createTutor();
                    case 2 -> listTutors();
                    case 3 -> registerPet();
                    case 4 -> transferPet();
                    case 5 -> deactivateTutor();
                    case 6 -> activateTutor();
                    case 7 -> findPetById();
                    case 0 -> System.out.println("Exiting system...");
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (option != 0);
    }

    // =========================
    // MENU
    // =========================

    private void printMenu() {
        System.out.println("\n=== VETERINARY CLINIC SYSTEM ===");
        System.out.println("1 - Create Tutor");
        System.out.println("2 - List Tutors");
        System.out.println("3 - Register Pet");
        System.out.println("4 - Transfer Pet");
        System.out.println("5 - Deactivate Tutor");
        System.out.println("6 - Activate Tutor");
        System.out.println("7 - Find Pet by ID");
        System.out.println("0 - Exit");
    }

    // =========================
    // OPERATIONS
    // =========================

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

        System.out.println("Address information:");

        String street = readStringOrCancel("Street: ");
        if (street == null) return;

        String number = readStringOrCancel("Number: ");
        if (number == null) return;

        String city = readStringOrCancel("City: ");
        if (city == null) return;

        String state = readStringOrCancel("State: ");
        if (state == null) return;

        String zip = readStringOrCancel("Zip Code: ");
        if (zip == null) return;

        String complement = readOptionalString("Complement (optional): ");

        Tutor tutor = tutorService.saveTutor(
                name, cpf, phone, email,
                new Address(street, number, city, state, zip, complement)
        );

        System.out.println("Tutor created. ID: " + tutor.getId());
    }

    private void listTutors() {

        List<Tutor> tutors = tutorService.getAllTutors();

        if (tutors.isEmpty()) {
            System.out.println("No tutors registered.");
            return;
        }

        for (Tutor t : tutors) {
            System.out.println("ID: " + t.getId());
            System.out.println("Name: " + t.getName());
            System.out.println("Status: " + t.getTutorStatus());
            System.out.println("------------------------");
        }
    }

    private void registerPet() {

        if (tutorService.getAllTutors().isEmpty()) {
            System.out.println("No tutors available. Create one first.");
            return;
        }

        System.out.println("\n--- Register Pet (0 to cancel) ---");

        String tutorId = readStringOrCancel("Tutor ID: ");
        if (tutorId == null) return;

        Tutor tutor = tutorService.getTutorById(tutorId);

        if (tutor.getTutorStatus() != TutorStatus.ACTIVE) {
            System.out.println("Tutor must be ACTIVE to register pet.");
            return;
        }

        String name = readStringOrCancel("Pet Name: ");
        if (name == null) return;

        String breed = readStringOrCancel("Breed: ");
        if (breed == null) return;

        double weight = readDoubleOrCancel("Weight: ");
        if (weight == -1) return;

        int year = readIntOrCancel("Birth Year: ");
        if (year == -1) return;

        int month = readIntOrCancel("Birth Month: ");
        if (month == -1) return;

        int day = readIntOrCancel("Birth Day: ");
        if (day == -1) return;

        AnimalSpecies species = AnimalSpecies.valueOf(
                readStringOrCancel("Species (DOG, CAT, etc): ").toUpperCase()
        );

        Pet pet = tutorService.registerPet(
                name, species, breed,
                LocalDate.of(year, month, day),
                weight,
                tutorId
        );

        System.out.println("Pet created. ID: " + pet.getId());
    }

    private void transferPet() {

        if (tutorService.getAllTutors().size() < 2) {
            System.out.println("At least two tutors required for transfer.");
            return;
        }

        System.out.println("\n--- Transfer Pet (0 to cancel) ---");

        String fromTutor = readStringOrCancel("Source Tutor ID: ");
        if (fromTutor == null) return;

        String toTutor = readStringOrCancel("Destination Tutor ID: ");
        if (toTutor == null) return;

        String petId = readStringOrCancel("Pet ID: ");
        if (petId == null) return;

        tutorService.transferPet(fromTutor, toTutor, petId);

        System.out.println("Pet transferred successfully.");
    }

    private void deactivateTutor() {

        String id = readStringOrCancel("Tutor ID: ");
        if (id == null) return;

        tutorService.deactivateTutor(id);
        System.out.println("Tutor deactivated.");
    }

    private void activateTutor() {

        String id = readStringOrCancel("Tutor ID: ");
        if (id == null) return;

        tutorService.activateTutor(id);
        System.out.println("Tutor activated.");
    }

    private void findPetById() {

        String id = readStringOrCancel("Pet ID: ");
        if (id == null) return;

        Pet pet = tutorService.getPetById(id);
        System.out.println(pet);
    }

    // =========================
    // INPUT HELPERS
    // =========================

    private String readStringOrCancel(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        return input.equals("0") ? null : input;
    }

    private String readOptionalString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private int readIntOrCancel(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        return input.equals("0") ? -1 : Integer.parseInt(input);
    }

    private double readDoubleOrCancel(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        return input.equals("0") ? -1 : Double.parseDouble(input);
    }
}