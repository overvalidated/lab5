package org.germanbeyger.lab5.cli;

import org.germanbeyger.lab5.TransmissionInterrupted;
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

    public static Ticket requestNewTicket(Scanner stdInScanner) throws TransmissionInterrupted {
        return requestNewTicket(stdInScanner, true);
    }

    public static Ticket requestNewTicket(Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        // Used to parse name field in Ticket class
        Function<String, String> nameParser = (input) -> { 
            if (input.isEmpty()) 
                throw new IllegalArgumentException("Name can't be an empty string");
            return input;
        };
        String name = FieldRequester.parseField(nameParser, "Enter a name: ", false, stdInScanner, showMessage);
        Coordinates coords = requestNewCoordinate(stdInScanner, showMessage);

        long price = FieldRequester.parseField(Long::parseLong, 
                "Enter a price (integer): ", false, stdInScanner, showMessage);
        
        Function<String, Double> discountParser = (input) -> { 
            double discount = Double.parseDouble(input);
            if (discount < 0 || discount > 100) 
                throw new IllegalArgumentException("Discount must be in range from 0 to 100");
            return discount;
        };
        double discount = FieldRequester.parseField(discountParser, 
                "Enter discount percent (0-100): ", false, stdInScanner, showMessage);

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
                "Is it refundable? (y/n): ", false, stdInScanner, showMessage);
        
        TicketType ticketType = requestNewTicketType(stdInScanner, showMessage);
        Person person = requestNewPerson(stdInScanner, showMessage);
        return new Ticket(name, coords, price, discount, refundable, ticketType, person);
    }

    public static Coordinates requestNewCoordinate(Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        Function<String, Long> xCoordinateParser = (input) -> {
            long x = Long.parseLong(input);
            if (x > 643) throw new IllegalArgumentException("X must be less or equal to 643");
            return x;
        };
        long x = FieldRequester.parseField(xCoordinateParser, "Enter x coordinate: ", false, stdInScanner, showMessage);
        double y = FieldRequester.parseField(Double::parseDouble, "Enter y coordinate: ", false, stdInScanner, showMessage);

        return new Coordinates(x, y);
    }


    public static TicketType requestNewTicketType(Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        return FieldRequester.parseField(
                TicketType::valueOf, 
                String.format("Enter ticket type (values allowed are %s): ", Arrays.asList(TicketType.values())), 
                false, stdInScanner, showMessage);
    }

    public static Color requestNewColor(String requestText, Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        return FieldRequester.parseField(Color::valueOf, 
                String.format("%s (values allowed are %s): ", requestText, Arrays.asList(Color.values())),
                false, stdInScanner, showMessage);
    }

    public static Color2 requestNewColor2(String requestText, Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        return FieldRequester.parseField(Color2::valueOf, 
                String.format("%s (values allowed are %s): ", requestText, Arrays.asList(Color2.values())),
                false, stdInScanner, showMessage);
    }

    public static Country requestNewCountry(Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        return FieldRequester.parseField(Country::valueOf, 
        String.format("Enter person's nationality (values allowed are %s): ", Arrays.asList(Country.values())),
        false, stdInScanner, showMessage);
    }

    public static Person requestNewPerson(Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        float height = FieldRequester.parseField(Float::parseFloat, 
                "Enter person's height (float): ", false, stdInScanner, showMessage);
        Color eyeColor = requestNewColor("Enter person's eye color ", stdInScanner, showMessage);
        Color2 hairColor = requestNewColor2("Enter person's hair color ", stdInScanner, showMessage);
        Country nationality = requestNewCountry(stdInScanner, showMessage);
        Location location = requestNewLocation(stdInScanner, showMessage);
        return new Person(height, eyeColor, hairColor, nationality, location);
    }
    
    public static Location requestNewLocation(Scanner stdInScanner, boolean showMessage) throws TransmissionInterrupted {
        double x = FieldRequester.parseField(Double::parseDouble, "Enter x coordinate: ", false, stdInScanner, showMessage);
        Long y = FieldRequester.parseField(Long::parseLong, "Enter y coordinate (Long or empty string): ", true, stdInScanner, showMessage);
        double z = FieldRequester.parseField(Double::parseDouble, "Enter z coordinate: ", false, stdInScanner, showMessage);

        return new Location(x, y, z);
    }
}
