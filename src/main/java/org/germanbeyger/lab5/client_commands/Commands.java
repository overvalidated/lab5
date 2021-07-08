package org.germanbeyger.lab5.client_commands;

import java.rmi.NoSuchObjectException;
import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.interfaces.ICommandCreator;
import org.germanbeyger.lab5.interfaces.IExecutor;

/**
 * Class for storing and invoking every available interactive command
 * <p>
 * Every command is stored as a pair of name and link to command "execute" in it's class.
 * When command is parsed, the name and arguments are passed to {@link #invokeCommand(String[], TargetCollection, Scanner)}
 * This method finds command and invokes lambda-expression.
 * Commands don't return anything.
 * <p>
 * z
 * k
 */
public enum Commands {
    ADD        ("add", Add::execute),
    ADD_IF_MAX ("add_if_max", AddIfMax::execute),
    CLEAR      ("clear", Clear::execute),
    FILTER_CONTAINS_NAME ("filter_contains_name", FilterContainsName::execute),
    HISTORY ("history", History::execute),
    INFO ("info", Info::execute),
    PRINT_DESCENDING  ("print_descending",  PrintDescending::execute),
    PRINT_UNIQUE_TYPE ("print_unique_type", PrintUniqueType::execute),
    REMOVE_BY_ID   ("remove_by_id",   RemoveById::execute),
    REMOVE_GREATER ("remove_greater", RemoveGreater::execute),
    SHOW   ("show", Show::execute),
    UPDATE ("update", Update::execute);

    private String    name;
    private ICommandCreator execCommand;

    Commands(String name, ICommandCreator execCommand) {
        this.name = name;
        this.execCommand = execCommand;
    }

    public String getName() {
        return this.name;
    }
    /**
     * 
     * @param args args[0] is command and args[1] - it's argument
     * @param targetCollection TargetCollection object
     * @param stdInScanner Scanner with System.in (passed from main)
     * @throws NoSuchObjectException if command was not found.
     */
    public static SendableCommand invokeCommand(String[] args, Scanner stdInScanner) throws NoSuchObjectException, TransmissionInterrupted {
        final String COMMAND_NAME = args[0];
        SendableCommand sendableCommand = null;
        
        switch (COMMAND_NAME) {
            case "execute_script": return ExecuteScript.executeScript(args[1]);
            case "exit": Exit.execute(); return null;
            case "help": Help.execute(); return null;
            default:
            for (Commands command : Commands.values()) {
                if (COMMAND_NAME.equals(command.getName())) {
                    sendableCommand = command.execute(args, stdInScanner);
                    return sendableCommand;
                }
            }
            throw new NoSuchObjectException (
                String.format("Command \"%s\" was not found.\n", COMMAND_NAME)
            );
        }

    }

    /**
     * 
     * @param args
     * @param targetCollection
     * @param stdInScanner
     */
    public SendableCommand execute(String[] args, Scanner stdInScanner) throws TransmissionInterrupted {
        return execCommand.execute(args, stdInScanner);
    }
}
