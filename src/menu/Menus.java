package src.menu;

public class Menus {

    public static void entryMenu(){
        System.out.println();
        System.out.println(" ----- Welcome  Make My Booking -----");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit...");
    }
    public static void adminMenu(){
        System.out.println();
        System.out.println("--------- Admin Menu ----------");
        System.out.println("1.Manage Theater/Owner ");
        System.out.println("2.Manage Movie");
        System.out.println("3.View Booking details");
        System.out.println("4.View Movie History");
        System.out.println("5. Logout...");
    }
    
    public static void theaterOwnerMenu()
    {
         System.out.println();
         System.out.println("-------- Theater Owner Menu---------");
        System.out.println("1. Manage Shows");
        System.out.println("2. Manage Seats");
        System.out.println("3. View Movie History");
        System.out.println("4. View Booking Details");
        System.out.println("5. View Payment Details");
        System.out.println("6. Logout...");

    }
    public static void viewersMenu()
    {
        System.out.println();
        System.out.println("------------Viewers Menu-------------");
        System.out.println("1. My profile");
        System.out.println("2. Search Movie");
        System.out.println("3. Book Movie Ticker");
        System.out.println("4. View Booking History");
        System.out.println("5. Cancel Booking");
        System.out.println("6. Logout...");
    
    }

    
}
