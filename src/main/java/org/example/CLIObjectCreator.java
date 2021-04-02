package org.example;

import java.util.Scanner;

public class CLIObjectCreator {
    public static Ticket requestNewTicket() {
        System.out.println("Enter a name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();


        return new Ticket();
    }

    public static Coordinates requestNewCoordinate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x coordinate: ");
        long x = sc.nextLong();
        System.out.println("Enter y coordinate: ");
        double y = sc.nextDouble();
        return new Coordinates(x, y);
    }

    public static Person requestNewPerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter person's height: ");
        float heightName = sc.nextFloat();

        String eyeColorName;
        Color eyeColor;

        String hairColorName;
        Color hairColor;

        boolean ask = true;

        while (ask) {
            try {
                System.out.println("Enter person's eye color: ");
                eyeColorName = sc.nextLine();
                eyeColor = Color.valueOf(eyeColorName);
                ask = false;
            } catch (IllegalArgumentException e) {
                System.out.println("This color doesn't exist");
            }
        }

        hairColor = requestEnum(requestText, errorText);

        return new Person();
    }

    public static <T extends Enum<?>> T requestEnum(String requestText, String errorText) {
        boolean ask = true;
        Scanner sc = new Scanner(System.in);
        String enumName;

        while (ask) {
            try {
                System.out.println("Enter person's eye color: ");
                enumName = sc.nextLine();
                return T.valueOf(enumName);
            } catch (IllegalArgumentException e) {
                System.out.println("This color doesn't exist");
            }
        }
    }
}
