package org.germanbeyger.lab5.cli;

import org.germanbeyger.lab5.datatypes.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Class containing methods for objects creation via command line.
 * <p>
 * This class can't be instanced as it's main aim is to provide methods that share the same purpose
 * <p>
 * 
 * 
 */
public final class CLIObjectCreator {
    // This class MUST NOT BE INSTANTIATED!
    private CLIObjectCreator() { }

    public static Ticket requestNewTicket(int id, Scanner stdInScanner) {
        // Used to parse name field in Ticket class
        Function<String, String> nameParser = (input) -> { 
            if (input == "") 
                throw new IllegalArgumentException("Name can't be an empty string");
            else
                return input;
        };
        String name = FieldRequester.parseField(nameParser, "Enter a name: ", false, stdInScanner);
        Coordinates coords = requestNewCoordinate(stdInScanner);

        long price = FieldRequester.parseField(Long::parseLong, 
                "Enter a price (integer): ", false, stdInScanner);
        
        Function<String, Double> discountParser = (input) -> { 
            double discount = Double.parseDouble(input);
            if (discount < 0 || discount > 100) 
                throw new IllegalArgumentException("Discount must be in range from 0 to 100");
            return discount;
        };
        double discount = FieldRequester.parseField(discountParser, 
                "Enter discount percent (0-100): ", false, stdInScanner);

        Function<String, Boolean> parseRefundable = (input) -> {
            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    throw new IllegalArgumentException("The field \"Refundable\" must be either \"y\" or \"n\"");
            }
        };
        boolean refundable = FieldRequester.parseField(parseRefundable,
                "Is it refundable? (y/n): ", false, stdInScanner);
        
        TicketType ticketType = requestNewTicketType(stdInScanner);
        Person person = requestNewPerson(stdInScanner);
        return new Ticket(id, name, coords, price, discount, refundable, ticketType, person);
    }

    public static Coordinates requestNewCoordinate(Scanner stdInScanner) {
        Function<String, Long> xCoordinateParser = (input) -> {
            long x = Long.parseLong(input);
            if (x > 643) throw new IllegalArgumentException("X must be less or equal to 643");
            return x;
        };
        long x = FieldRequester.parseField(xCoordinateParser, "Enter x coordinate: ", false, stdInScanner);
        double y = FieldRequester.parseField(Double::parseDouble, "Enter y coordinate: ", false, stdInScanner);

        return new Coordinates(x, y);
    }


    public static TicketType requestNewTicketType(Scanner stdInScanner) {
        return FieldRequester.parseField(
                TicketType::valueOf, 
                String.format("Enter ticket type (values allowed are %s): ", Arrays.asList(TicketType.values())), 
                false, stdInScanner);
    }

    public static Color requestNewColor(String requestText, Scanner stdInScanner) {
        return FieldRequester.parseField(Color::valueOf, 
                String.format("%s (values allowed are %s): ", requestText, Arrays.asList(Color.values())),
                false, stdInScanner);
    }

    public static Color2 requestNewColor2(String requestText, Scanner stdInScanner) {
        return FieldRequester.parseField(Color2::valueOf, 
                String.format("%s (values allowed are %s): ", requestText, Arrays.asList(Color2.values())),
                false, stdInScanner);
    }

    public static Country requestNewCountry(Scanner stdInScanner) {
        return FieldRequester.parseField(Country::valueOf, 
        String.format("Enter person's nationality (values allowed are %s): ", Arrays.asList(Country.values())),
        false, stdInScanner);
    }

    public static Person requestNewPerson(Scanner stdInScanner) {
        float height = FieldRequester.parseField(Float::parseFloat, 
                "Enter person's height (float): ", false, stdInScanner);
        Color eyeColor = requestNewColor("Enter person's eye color ", stdInScanner);
        Color2 hairColor = requestNewColor2("Enter person's hair color ", stdInScanner);
        Country nationality = requestNewCountry(stdInScanner);
        Location location = requestNewLocation(stdInScanner);
        return new Person(height, eyeColor, hairColor, nationality, location);
    }
    
    public static Location requestNewLocation(Scanner stdInScanner) {
        double x = FieldRequester.parseField(Double::parseDouble, "Enter x coordinate: ", false, stdInScanner);
        Long y = FieldRequester.parseField(Long::parseLong, "Enter y coordinate (Long or empty string): ", true, stdInScanner);
        double z = FieldRequester.parseField(Double::parseDouble, "Enter z coordinate: ", false, stdInScanner);

        return new Location(x, y, z);
    }
}
