package org.germanbeyger.lab5.cli;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import org.germanbeyger.lab5.App;

/**
 * 
 */
public class FieldRequester {
    public static boolean fromFile = false;

    /**
     * 
     * @param <T>
     * @param stringParser
     * @param requestMessage
     * @param supportNull
     * @param stdInScanner
     * @return
     */
    public static <T> T parseField(Function<String, T> stringParser,
             String requestMessage, boolean supportNull, Scanner stdInScanner) {
        
        T parsedValue = null;
        while (true) {
            // Prompting for input
            System.out.println(requestMessage);
            String requestString = null;
            try {
                // Retrieving the entire line
                requestString = stdInScanner.nextLine();
            } catch (NoSuchElementException | IllegalStateException e) {
                // If no line -> exit the app. It's most probably a ctrl+d. 
                System.out.println("End of transmission. Interrupting execution...");
                //throw new NoSuchElementException()
                System.exit(1);
            }
            // If result is an empty string and we support empty values (aka nulls) then return null;
            if (supportNull && requestString.equals("")) 
                break; 
            // If not empty then try to parse the value.
            // If we can't parse then ask to enter the value again.
            try {
                // We use lambda-function passed as first argument
                parsedValue = stringParser.apply(requestString);
                break;
            } catch (Exception e) {
                if (fromFile) throw new IllegalArgumentException("Input in script doesn't fit the conditions it's supposed to.");
                System.out.println("Something went wrong while parsing. Please enter the value again.");
            }
        }
        return parsedValue;
    }

    /**
     * 
     * @param <T> 
     * @param args
     * @param parseFunction
     * @return
     * @throws IllegalArgumentException
     */
    public static <T> T retrieveArgument(String[] args, Function<String, T> parseFunction) throws IllegalArgumentException  {
        if (args.length != 2) { 
            throw new IllegalArgumentException(String.format("Expected 2 arguments, got %d\n", args.length));
        }
        try {
            return parseFunction.apply(args[1]);
        } catch (Exception e) { // Actually, we don't know what to catch here. And we don't care. 
            throw new IllegalArgumentException("Error while parsing an argument. " +  
                    "Ensure that the value is in correct format. ");
        }
    }
}
