package org.germanbeyger.lab5;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.commands.Commands;

public class App {
    private static TargetCollection targetCollection;
    static String collectionPath = "collection.xml";
    
    public static void promptNewCollection(Scanner stdInScanner) {
        //
        Function<String, Boolean> parseAnswer = (input) -> {
            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    throw new IllegalArgumentException("This field must be either \"y\" or \"n\"");
            }
        };
        boolean createNew = FieldRequester.parseField(parseAnswer,
                "Create new? (y/n): ", false, stdInScanner);
        if (!createNew)
            System.exit(0);
        collectionPath = FieldRequester.parseField((input) -> input, "Enter filename: ", false, stdInScanner);
        targetCollection = new TargetCollection();
    }

    public static void main(String[] args ) {
        try {
        // we expect only filepath as arguments
        if (args.length != 1) {
            System.out.printf("Wrong number of arguments: expected 1, got %d\n", args.length);
            return;
        }

        collectionPath = args[0];
        targetCollection = XMLCollectionProcessor.load(collectionPath);
        if (targetCollection != null) {
            if (!targetCollection.verify()) {
                System.out.println("Loaded collection is corrupted. ");
                targetCollection = null;
            }
        }
        // если не проходим верификацию - то выкидываем коллекцию
        try (Scanner stdInScanner = new Scanner(System.in)) {
            // Если коллекция не загружена - запрашиваем создание новой
            if (targetCollection == null) {
                promptNewCollection(stdInScanner);
            }
            String nextCommand = null;
            while (true) {
                nextCommand = stdInScanner.nextLine();
                executeCommand(nextCommand, stdInScanner);
            }
        }  catch (NoSuchElementException e) { 
            System.out.println("End of transmission. Interrupting execution...");
            return;
        }
    } catch (Exception e) {
        System.out.println("Unexpected uncatch exception: exiting...");
    }
    }

    public static void executeCommand(String command, Scanner stdInScanner) {
        String[] commandArgs = command.split(" ");
        // commandArgs[0] is a main command
        // commangdArgs[1] is an argument.
        // костыль... если сохраняем xml, путь помещаем как аргумент
        if (commandArgs[0].equals("save")) {
            String[] args = new String[2];
            args[0] = commandArgs[0];
            args[1] = collectionPath;
            commandArgs = args;
        }
        try {
            Commands.invokeCommand(commandArgs, targetCollection, stdInScanner);
        } catch (NoSuchObjectException e) {
            System.out.println("The command you've just entered isn't presented in list of available commands.");
            Commands.HELP.execute(commandArgs, targetCollection, stdInScanner);
        }
    }
}
