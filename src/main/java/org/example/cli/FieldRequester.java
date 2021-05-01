package org.example;

import java.io.BufferedInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

public class FieldRequester {
    public static <T> T parseField(Function<String, T> stringParser,
             String requestMessage, boolean supportNull, Scanner scanner) {
        
        T parsedValue = null;
        while (true) {
            System.out.println(requestMessage);
            String requestString = null;
            try {
                requestString = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("End of transmission. Interrupting execution...");
                System.exit(1);
            }
            if (supportNull && requestString == "") {
                break;
            }
            try {
                parsedValue = stringParser.apply(requestString);
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong while parsing. Please enter the value again.");
            }
        }
        return parsedValue;
    }
}
