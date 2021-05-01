package org.example;

import java.util.Scanner;
import java.util.function.Function;

public class CLIObjectCreator {
    // for debug purposes
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { 
            requestNewTicket(0, scanner);
        }
    }

    public static Ticket requestNewTicket(int id, Scanner scanner) {
        Function<String, String> nameParser = (input) -> { 
            if (input == "") 
                throw new IllegalArgumentException("String can't be empty");
            else
                return input;
        };
        String name = FieldRequester.parseField(nameParser, "Enter a name: ", true, scanner);
        Coordinates coords = requestNewCoordinate(scanner);

        Long price = FieldRequester.parseField(Long::parseLong, 
                "Enter a price (integer or empty string): ", true, scanner);
        

        Double discount = FieldRequester.parseField(Double::parseDouble, 
                "Enter discount percent (0-100 or empty string): ", true, scanner);
                
        
        Boolean refundable = FieldRequester.parseField(Boolean::parseBoolean,
                "Is it refundable? (true/false/empty string): ", true, scanner);
        
        
        TicketType ticketType = requestNewTicketType(scanner);
        Person person = requestNewPerson(scanner);
        return new Ticket(id, name, coords, price, discount, refundable, ticketType, person);
    }

    public static Coordinates requestNewCoordinate(Scanner scanner) {
        long x = FieldRequester.parseField(Long::parseLong, "Enter x coordinate:", false, scanner);
        double y = FieldRequester.parseField(Double::parseDouble, "Enter y coordinate:", false, scanner);

        return new Coordinates(x, y);
    }


    public static TicketType requestNewTicketType(Scanner scanner) {
        return FieldRequester.parseField(TicketType::valueOf, "Enter ticket type: ", false, scanner);
    }

    public static Color requestNewColor(String requestText, Scanner scanner) {
        return FieldRequester.parseField(Color::valueOf, requestText, false, scanner);
    }

    public static Country requestNewCountry(Scanner scanner) {
        return FieldRequester.parseField(Country::valueOf, "Enter person's nationality: ", false, scanner);
    }

    public static Person requestNewPerson(Scanner scanner) {
        Float height = FieldRequester.parseField(Float::parseFloat, 
                "Enter person's height (float or empty string)", true, scanner);
        Color eyeColor = requestNewColor("Enter person's eye color: ", scanner);
        Color hairColor = requestNewColor("Enter person's hair color: ", scanner);
        Country nationality = requestNewCountry(scanner);
        Location location = requestNewLocation(scanner);
        return new Person(height, eyeColor, hairColor, nationality, location);
    }
    
    public static Location requestNewLocation(Scanner scanner) {
        double x = FieldRequester.parseField(Double::parseDouble, "Enter x coordinate: ", false, scanner);
        Long y = FieldRequester.parseField(Long::parseLong, "Enter y coordinate (Long or empty string): ", true, scanner);
        double z = FieldRequester.parseField(Double::parseDouble, "Enter z coordinate: ", false, scanner);

        return new Location(x, y, z);
    }
}
