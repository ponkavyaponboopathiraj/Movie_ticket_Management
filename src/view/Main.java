package src.view;

import src.menu.Menus;
import src.util.InputUtil;
import src.model.dto.User;
import src.controller.UserController;

public class Main {
    private static final UserController userController = new UserController();

    public static void main(String[] args) {
        while (true) {
            Menus.entryMenu();
            int choice = InputUtil.getIntInput();

            switch (choice) {
                case 1 -> loginUser();
                case 2 -> registerUser();
                case 3 -> {
                    System.out.println("Exiting... Thank you for using Make My Booking!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.println("\n--- Viewer Registration ---");
        System.out.print("Enter name: ");
        String name = InputUtil.getStringInput();
        System.out.print("Enter email: ");
        String email = InputUtil.getStringInput();
        System.out.print("Enter password: ");
        String password = InputUtil.getStringInput();
        System.out.print("Enter phone number: ");
        long phone = InputUtil.getLongInput();

        // Only viewers can register
        String role = "viewer";

        boolean isRegistered = userController.registerUser(name, email, password, phone, role);
        if (isRegistered)
            System.out.println("Registration successful! You can now login.");
        else
            System.out.println("Registration failed! Email may already exist.");
    }

    private static void loginUser() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter email: ");
        String email = InputUtil.getStringInput();
        System.out.print("Enter password: ");
        String password = InputUtil.getStringInput();

        User user = userController.login(email, password);

        if (user == null) {
            System.out.println("Invalid credentials. Try again.");
            return;
        }

        System.out.println("Login successful! Welcome, " + user.getName() + ".");
        String role = user.getRole().toLowerCase();

        switch (role) {
            case "admin" -> adminMenuFlow();
            case "theater_owner" -> theaterOwnerMenuFlow();
            case "viewer" -> viewerMenuFlow();
            default -> System.out.println("Unknown role. Contact admin.");
        }
    }

    private static void adminMenuFlow() {
        while (true) {
            Menus.adminMenu();
            int choice = InputUtil.getIntInput();
            switch (choice) {
                case 1 -> System.out.println("Manage Theater/Owner");
                case 2 -> System.out.println("Manage Movie");
                case 3 -> System.out.println("View Booking Details");
                case 4 -> System.out.println("View Movie History");
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void theaterOwnerMenuFlow() {
        while (true) {
            Menus.theaterOwnerMenu();
            int choice = InputUtil.getIntInput();
            switch (choice) {
                case 1 -> System.out.println("Manage Shows");
                case 2 -> System.out.println("Manage Seats");
                case 3 -> System.out.println("View Movie History");
                case 4 -> System.out.println("View Booking Details");
                case 5 -> System.out.println("View Payment Details");
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewerMenuFlow() {
        while (true) {
            Menus.viewersMenu();
            int choice = InputUtil.getIntInput();
            switch (choice) {
                case 1 -> System.out.println("My Profile");
                case 2 -> System.out.println("Search Movie");
                case 3 -> System.out.println("Book Movie Ticket");
                case 4 -> System.out.println("View Booking History");
                case 5 -> System.out.println("Cancel Booking");
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
