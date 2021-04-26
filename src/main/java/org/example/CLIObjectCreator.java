package org.example;

import java.util.InputMismatchException;

public class CLIObjectCreator {

    public static void main(String[] args) {
        requestNewTicket();
    }

    public static Ticket requestNewTicket() {
        System.out.println("Enter a name: ");
        String name = App.globalScanner.nextLine();
        Coordinates coords = requestNewCoordinate();

        Long price = null;
        price_request_loop:
        while (true) {
            System.out.println("Enter a price (integer or null): ");
            String priceString = App.globalScanner.nextLine();
            switch (priceString) {
                case "null": break price_request_loop;
                default: try { 
                    price = Long.parseLong(priceString); break price_request_loop; 
                } catch (NumberFormatException e) {
                    System.out.println("Problem occured while parsing your input. " + 
                    "Please, ensure that the value you've entered is correct. ");
                }
            }
        }

        Double discount = null;
        discout_request_loop:
        while (true) {
            System.out.println("Enter discount percent (0-100 or null): ");
            String discountString = App.globalScanner.nextLine();
            switch (discountString) {
                case "null": break discout_request_loop;
                default: 
                    try { 
                        discount = Double.parseDouble(discountString); break discout_request_loop; 
                    } catch (NumberFormatException e) {
                        System.out.println("Problem occured while parsing your input. " + 
                        "Please, ensure that the value you've entered is correct. ");
                    }
            }
        }
        
        Boolean refundable = null;
        while (true) {
            System.out.println("Is it refundable? (true/false/null): ");
            String refundableString = App.globalScanner.nextLine();
            if (refundableString.equals("true")) {
                refundable = true;
            } else if (refundableString.equals("false")) {
                refundable = false;
            } else if (!refundableString.equals("null")) {
                System.out.println("Enter one of true/false/null values");
                continue;
            }
            break;
        }
        
        
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
                x = Long.parseLong(App.globalScanner.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("There was a problem during parsing the passed value. Enter the value again (e.g. 123)");
            }
        }
        while (true) {
            try {
            System.out.println("Enter y coordinate: ");
            y = Double.parseDouble(App.globalScanner.nextLine());
            break;
            } catch (InputMismatchException e) {
                System.out.println("There was a problem during parsing the passed value. Enter the value again (e.g. 42,5)");
            }
        }
        return new Coordinates(x, y);
    }


    public static TicketType requestNewTicketType() {
        String ticketTypeName;
        while (true) {
            try {
                System.out.println("Enter ticket type: ");
                ticketTypeName = App.globalScanner.nextLine();
                return TicketType.valueOf(ticketTypeName.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("This ticket type doesn't exist");
            } 
        }
    }

    public static Color requestNewColor(String requestText) {
        String colorName;
        while (true) {
            try {
                System.out.println(requestText);
                colorName = App.globalScanner.nextLine();
                return Color.valueOf(colorName.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("This color doesn't exist");
            }
        }
    }
    public static Country requestNewCountry() {
        String countryName;
        while (true) {
            System.out.println("Enter person's nationality: ");
            countryName = App.globalScanner.nextLine();
            try {
                return Country.valueOf(countryName.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("This country doesn't exist");
            }
        }
    }

    public static Person requestNewPerson() {
        System.out.println("Enter person's height: ");
        Float height = null;
        height_request_loop:
        while (true) {
            System.out.println("Enter a person's height (float or null): ");
            String heightString = App.globalScanner.nextLine();
            switch (heightString) {
                case "null": break height_request_loop;
                default: try { 
                    height = Float.parseFloat(heightString); break height_request_loop; 
                } catch (NumberFormatException e) {
                    System.out.println("Problem occured while parsing your input. " + 
                    "Please, ensure that the value you've entered is correct. ");
                }
            }
        }
        Color eyeColor = requestNewColor("Enter person's eye color: ");
        Color hairColor = requestNewColor("Enter person's hair color: ");
        Country nationality = requestNewCountry();
        Location location = requestNewLocation();
        return new Person(height, eyeColor, hairColor, nationality, location);
    }
    
    public static Location requestNewLocation() {
        double x;
        Long y = null;
        double z;

        while (true) {
            try {
            System.out.println("Enter x coordinate: ");
            x = Double.parseDouble(App.globalScanner.nextLine());
            break;
            } catch (InputMismatchException e) {
                System.out.println("There was a problem during parsing the passed value. Enter the value again (e.g. 42,5)");
            }
        }

        y_coord_request_loop:
        while (true) {
            System.out.println("Enter y coordinate (Long or null): ");
            String yCoord = App.globalScanner.nextLine();
            switch (yCoord) {
                case "null": break y_coord_request_loop;
                default: try { 
                    y = Long.parseLong(yCoord); break y_coord_request_loop; 
                } catch (NumberFormatException e) {
                    System.out.println("Problem occured while parsing your input. " + 
                    "Please, ensure that the value you've entered is correct. ");
                }
            }
        }
        while (true) {
            try {
            System.out.println("Enter z coordinate: ");
            z = Double.parseDouble(App.globalScanner.nextLine());
            break;
            } catch (InputMismatchException e) {
                System.out.println("There was a problem during parsing the passed value. Enter the value again (e.g. 42,5)");
            }
        }
        return new Location(x, y, z);
    }
}
