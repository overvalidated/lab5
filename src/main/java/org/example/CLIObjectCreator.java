package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLIObjectCreator {

    public static void main(String[] args) {
        requestNewTicket();
    }

    public static Ticket requestNewTicket() {
        System.out.println("Enter a name: ");
        String name = App.globalScanner.nextLine();

        Coordinates coords = requestNewCoordinate();

        System.out.println("Enter a price: ");
        long price = App.globalScanner.nextLong();
        System.out.println("Enter discount percent (0-100): ");
        double discount = App.globalScanner.nextDouble();
        System.out.println("Is it refundable? (true/false): ");
        boolean refundable = App.globalScanner.nextBoolean();
        
        TicketType ticketType = requestNewTicketType();
        Person person = requestNewPerson();
        return new Ticket(name, coords, price, discount, refundable, ticketType, person);
    }

    public static Coordinates requestNewCoordinate() {
        long x;
        double y;

        while (true) {
            try {
            System.out.println("Enter x coordinate: ");
            x = App.globalScanner.nextLong();
            break;
            } catch (InputMismatchException e) {
                System.out.println("There was a problem when parsing the passed value. Repeat te input (e.g. 123)");
            }
        }
        while (true) {
            try {
            System.out.println("Enter y coordinate: ");
            y = App.globalScanner.nextDouble();
            break;
            } catch (InputMismatchException e) {
                System.out.println("There was a problem when parsing the passed value. Repeat te input (e.g. 42,5)");
            }
        }
        return new Coordinates(x, y);
    }


    public static TicketType requestNewTicketType() {
        String ticketTypeName;
        while (true) {
            try {
                System.out.println("Enter person's eye color: ");
                ticketTypeName = App.globalScanner.nextLine();
                return TicketType.valueOf(ticketTypeName);
            } catch (IllegalArgumentException e) {
                System.out.println("This color doesn't exist");
            }
        }
    }

    public static Color requestNewColor(String requestText) {
        String colorName;
        while (true) {
            try {
                System.out.println(requestText);
                colorName = App.globalScanner.nextLine();
                return Color.valueOf(colorName);
            } catch (IllegalArgumentException e) {
                System.out.println("This color doesn't exist");
            }
        }
    }
    public static Country requestNewCountry() {
        String countryName;
        while (true) {
            try {
                System.out.println("Enter person's nationality color: ");
                countryName = App.globalScanner.nextLine();
                return Country.valueOf(countryName);
            } catch (IllegalArgumentException e) {
                System.out.println("This country doesn't exist");
            }
        }
    }

    public static Person requestNewPerson() {
        System.out.println("Enter person's height: ");
        float height = App.globalScanner.nextFloat();
        Color eyeColor = requestNewColor("Enter person's eye color: ");
        Color hairColor = requestNewColor("Enter person's hair color: ");
        Country nationality = requestNewCountry();
        Location location = requestNewLocation();
        return new Person(height, eyeColor, hairColor, nationality, location);
    }

    public static Location requestNewLocation() {

        System.out.println("Enter x coordinate: ");
        double x = App.globalScanner.nextDouble();
        System.out.println("Enter y coordinate: ");
        long y = App.globalScanner.nextLong();
        System.out.println("Enter z coordinate: ");
        double z = App.globalScanner.nextDouble();

        return new Location(x, y, z);
    }
}
