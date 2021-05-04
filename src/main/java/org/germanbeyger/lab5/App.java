package org.germanbeyger.lab5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

import com.thoughtworks.xstream.XStreamException;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;
import org.germanbeyger.lab5.datatypes.TicketType;

public class App {
    private static TargetCollection targetCollection;
    static final boolean DEBUG = false;
    static String filepath = "collection.xml";

    public static void main(String[] args ) {
        // we expect only filepath as arguments
        if (args.length != 1 && !DEBUG) {
            System.out.printf("Wrong number of arguments: expected 1, got %d\n", args.length);
            return;
        }
        try {
            if (!DEBUG) targetCollection = XMLCollectionProcessor.load(args[0]);
            else targetCollection = new TargetCollection();
        } catch (XStreamException e) {
            System.out.println("Can't load a collection. Exiting...");
            return;
        }

        // interactive mode
        try (Scanner stdInScanner = new Scanner(System.in)) {
            String nextCommand = null;
            while (true) {
                nextCommand = stdInScanner.nextLine();
                executeCommand(nextCommand, stdInScanner);
            }
        } catch (NoSuchElementException e) { 
            System.out.println("End of transmission. Interrupting execution...");
            return;
        }
        // loading data from file
        // data loaded
    }

    public static void execute_script(String scriptPath, Scanner stdInScanner) {
        try (FileInputStream fstream = new FileInputStream(scriptPath);) {
            InputStreamReader inputStream = new InputStreamReader(fstream);
            BufferedReader bufStream = new BufferedReader(inputStream);

            String line = null;  
            while ((line = bufStream.readLine()) != null) {  
                executeCommand(line, stdInScanner);
            } 
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found.\nPrefer absolute path over relative.", scriptPath);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong while executing a script. ");
        }
    }

    public static void executeCommand(String command, Scanner stdInScanner) {
        String[] commandArgs = command.split(" ");
        // commandArgs[0] is a main command
        // commangdArgs[1] is an argument.
        switch (commandArgs[0]) {
            case "add": // добавить новый элемент в коллекцию
                targetCollection.add(CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner));
                break;

            case "remove_by_id": // удалить элемент из коллекции по его id
                try {
                    int idx = FieldRequester.retrieveArgument(commandArgs, Integer::parseInt);
                    targetCollection.remove(idx);
                } catch (IllegalArgumentException e) {
                    System.out.println("The argument to this command must be an integer. ");
                }
                break; 

            case "update": // обновить значение элемента коллекции, id которого равен заданному
                try {
                    int idx = FieldRequester.retrieveArgument(commandArgs, Integer::parseInt);
                    Ticket replacementTicket = CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner);
                    targetCollection.update(idx, replacementTicket);
                } catch (IllegalArgumentException e) {
                    System.out.println("The argument to this command must be an integer. ");
                }
                break;

            case "show": // вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                for (Ticket ticket : targetCollection)
                    System.out.println(ticket);

            case "clear": // очистить коллекцию
                targetCollection.clear();
                break;

            case "save": // сохранить коллекцию в файл
                XMLCollectionProcessor.save(targetCollection, filepath);
                break;

            case "exit": // завершить программу (без сохранения в файл)
                System.exit(0);
                break;

            case "add_if_max": 
                // добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                targetCollection.addIfMax(
                    CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner)
                );
                break;

            case "remove_greater": // удалить из коллекции все элементы, превышающие заданный
                Ticket ticketToCompare = CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner);
                targetCollection.removeIf(ticket -> ticket.compareTo(ticketToCompare) > 0);
                break;

            case "history": // вывести последние 11 команд (без их аргументов)
                System.out.print(targetCollection.getStringifiedHistory());
                break;

            case "filter_contains_name": // вывести элементы, значение поля name которых содержит заданную подстроку
                try {
                    String name = FieldRequester.retrieveArgument(commandArgs, (input) -> input);
                    LinkedList<Ticket> filteredCollection = targetCollection.filter(input -> input.getName().equals(name));
                    for (Ticket ticket : filteredCollection)
                        System.out.println(ticket);
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "print_descending": // вывести элементы коллекции в порядке убывания
                for (Ticket ticket : targetCollection.getSortedCollection()) 
                    System.out.println(ticket);
                break;

            case "print_unique_type": // вывести уникальные значения поля type всех элементов в коллекции
                TreeSet<TicketType> uniqueTypes = new TreeSet<>();
                for (Ticket ticket : targetCollection) 
                    uniqueTypes.add(ticket.getType());
                System.out.printf("Unique types in collection: %s\n", uniqueTypes);
                break;

            case "execute_script": /* Считать и исполнить скрипт из указанного файла. 
                В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. */
                try {
                    String scriptPath = FieldRequester.retrieveArgument(commandArgs, (input) -> input);
                    execute_script(scriptPath, stdInScanner);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "info": 
                // вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                System.out.println(targetCollection.getInfoAboutCollection());
                break;
                
            case "help": // вывести справку по доступным командам
                break;
                
            default:
                // For any other command - show help
                System.out.println("The command you've just entered isn't presented in list of available commands.");
                // showUsage();
                break;
        }
    }
}
