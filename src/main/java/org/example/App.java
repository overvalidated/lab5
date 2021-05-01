package org.example;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private static TargetCollection targetCollection;
    static boolean DEBUG = true;

    // todo breaks solid!!
    public static void executeCommand(String command, Scanner scanner) {
        String[] commandArgs = command.split(" ");
        // commandArgs[0] is a main command
        // commangdArgs[1] is an argument.
        switch (commandArgs[0]) {
            case "add": // добавить новый элемент в коллекцию
                targetCollection.add(CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), scanner));
                break;
            case "remove_by_id": // удалить элемент из коллекции по его id
                if (commandArgs.length != 2) { 
                    System.out.printf("Expected 2 arguments, got %d\n", commandArgs.length); 
                    return; 
                }
                try {
                    int idx = Integer.parseInt(commandArgs[1]);
                    targetCollection.remove(idx);
                } catch (NumberFormatException e) {
                    System.out.println("The argument to this command must be an integer. ");
                }
                break; 
            case "update": // обновить значение элемента коллекции, id которого равен заданному
                if (commandArgs.length != 2) { 
                    System.out.printf("Expected 2 arguments, got %d\n", commandArgs.length); 
                    return; 
                }
                try {
                    int idx = Integer.parseInt(commandArgs[1]);
                    targetCollection.remove(idx);
                } catch (NumberFormatException e) {
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
                XMLCollectionProcessor.save(targetCollection);
                break;
            case "exit": // завершить программу (без сохранения в файл)
                System.exit(0);
                break;
            case "add_if_max": 
                // добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                break;
            case "remove_greater": // удалить из коллекции все элементы, превышающие заданный
                break;
            case "history": // вывести последние 11 команд (без их аргументов)
                break;
            case "filter_contains_name": // вывести элементы, значение поля name которых содержит заданную подстроку
                break;
            case "print_descending": // вывести элементы коллекции в порядке убывания
                break;
            case "print_unique_type": // вывести уникальные значения поля type всех элементов в коллекции
                break;
            case "execute_script": /* Считать и исполнить скрипт из указанного файла. 
                В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. */
                break;
            case "info": 
                // вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                System.out.println(targetCollection);
                break;
            case "help": // вывести справку по доступным командам
                break;
            default:
                // For any other command - show help
                // showUsage();
                break;
        }
    }
    public static void main(String[] args ) {
        // we expect only filepath as arguments
        if (args.length != 1 && !DEBUG) {
            System.out.printf("Wrong number of arguments: expected 1, got %d\n", args.length);
            return;
        }
        if (DEBUG) targetCollection = new TargetCollection();

        // interactive mode
        try (Scanner scanner = new Scanner(System.in)) {
            String nextCommand = null;
            while (true) {
                nextCommand = scanner.nextLine();
                executeCommand(nextCommand, scanner);
            }
        } catch (NoSuchElementException e) { 
            System.out.println("End of transmission. Interrupting execution...");
            System.exit(1);
        }
        // loading data from file
        // data loaded
    }
}
