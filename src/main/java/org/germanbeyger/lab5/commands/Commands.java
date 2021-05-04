package org.germanbeyger.lab5.commands;

import java.rmi.NoSuchObjectException;
import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.interfaces.IExecutor;

/**
 * Class for storing and invoking every available command
 * <p>
 * <p>
 * 
 * 
 */
public enum Commands {
    ADD        ("add", Add::execute),
    ADD_IF_MAX ("add_if_max", AddIfMax::execute),
    CLEAR      ("clear", Clear::execute),
    EXECUTE_SCRIPT ("execute_script", ExecuteScript::execute),
    EXIT ("exit", Exit::execute),
    FILTER_CONTAINS_NAME ("filter_contains_name", FilterContainsName::execute),
    HELP ("help", Help::execute),
    HISTORY ("history", History::execute),
    INFO ("info", Info::execute),
    PRINT_DESCENDING  ("print_descending",  PrintDescending::execute),
    PRINT_UNIQUE_TYPE ("print_unique_type", PrintUniqueType::execute),
    REMOVE_BY_ID   ("remove_by_id",   RemoveById::execute),
    REMOVE_GREATER ("remove_greater", RemoveGreater::execute),
    SAVE   ("save", Save::execute),
    SHOW   ("show", Show::execute),
    UPDATE ("update", Update::execute);

    private String    name;
    private IExecutor execCommand;

    Commands(String name, IExecutor execCommand) {
        this.name = name;
        this.execCommand = execCommand;
    }

    public String getName() {
        return this.name;
    }

    public static void invokeCommand(String[] args, TargetCollection targetCollection, Scanner stdInScanner) throws NoSuchObjectException {
        final String COMMAND_NAME = args[0];
        for (Commands command : Commands.values()) {
            if (COMMAND_NAME.equals(command.getName())) {
                command.execute(args, targetCollection, stdInScanner);
                return;
            }
        }
        throw new NoSuchObjectException(
            String.format("Command \"%s\" was not found.", COMMAND_NAME)
        );
    }

    public void execute(String[] args, TargetCollection targetCollection, Scanner stdInScanner) {
        execCommand.execute(args, targetCollection, stdInScanner);
        targetCollection.addToHistory(this.name);
    }
}
