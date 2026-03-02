package application;

import infrastructure.repository.InMemoryTutorRepository;
import service.TutorService;

public class Main {

    public static void main(String[] args) {

        TutorService tutorService =
                new TutorService(new InMemoryTutorRepository());

        ConsoleMenu menu = new ConsoleMenu(tutorService);
        menu.start();
    }
}