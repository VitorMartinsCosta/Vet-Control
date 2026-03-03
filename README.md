# 🐾 Veterinary Clinic Management System

A console-based backend system for managing tutors and pets in a veterinary clinic.

This project was built to demonstrate solid understanding of object-oriented programming, domain modeling, layered architecture, and business rule enforcement in Java.

---

## 📌 Overview

The system allows:

- Tutor registration (unique CPF)
- Tutor activation and deactivation
- Updating tutor contact information
- Pet registration
- Listing pets by tutor
- Pet transfer between tutors
- Marking pets as deceased
- Business rule validation (status control, integrity enforcement)

All operations are performed via a structured console menu with user-friendly navigation.

---

## 🧱 Architecture

The project follows a layered architecture:
src/
├── application/ # Console entry point and user interaction
├── domain/
│ ├── model/ # Core business entities (Tutor, Pet, Address)
│ ├── enums/ # Domain state representations
│ ├── exceptions/ # Business and validation exceptions
│ └── repository/ # Repository interfaces (contracts)
├── infrastructure/
│ └── repository/ # InMemory repository implementation
└── service/ # Application services (use case orchestration)



### Key Architectural Decisions

- **Tutor is the Aggregate Root**
- Pets cannot exist without a Tutor
- No `PetRepository` (access always through Tutor)
- Domain layer protects invariants
- Service layer orchestrates inter-aggregate rules
- Soft delete implemented via TutorStatus (ACTIVE / INACTIVE)
- Pet lifecycle controlled by PetStatus (ACTIVE / DECEASED)

---

## 🧠 Business Rules Implemented

- CPF must be unique per tutor
- Inactive tutors cannot register new pets
- Pets must be ACTIVE to be transferred
- Tutor cannot transfer pet to themselves
- Pet marked as DECEASED cannot be modified
- All domain validations are enforced inside the aggregate

---

## 🛠 Technologies Used

- Java (OOP principles)
- Layered architecture
- In-memory repository implementation
- Stream API
- Optional
- Custom exception handling

No external frameworks were used to focus on core design and domain modeling.

---

## 🎯 Why This Project?

The goal was to:

- Practice Domain-Driven Design fundamentals
- Implement a rich domain model (not an anemic model)
- Demonstrate business rule consistency
- Build a system with clean separation of concerns
- Focus on reasoning and architecture rather than framework usage

---

## 🚀 Future Improvements

Planned evolutions:

- Persistence with JPA + H2
- REST API with Spring Boot
- Appointment management module
- Clinical history module
- Unit tests for domain and service layers
- Input validation refinement
- Logging system
- Transaction management

---

## ▶ How to Run

1. Clone the repository
2. Compile and run `Main.java`
3. Use the console menu to interact with the system

---

## 📈 Project Status

Functional MVP (Minimum Viable Product)

Architecture ready for scaling.

---

## 👨‍💻 Author

Vitor Martins Costa
