package org.germanbeyger.lab5.commands;

import java.io.Serializable;
import java.io.ObjectOutputStream;

import org.germanbeyger.lab5.interfaces.IExecutor;


/**
 * Class used for commands serialization
 */
public abstract class Command implements IExecutor, Serializable {
    private String[] args;
    // It's unsafe to pass the whole command as it can override the action.
    private int commandNumber;

    public Command(String[] args, final String commandName) {
        this.args = args;
        for (Commands commands : Commands.values()) {
            if (commands.getName().equals(commandName)) {
                commandNumber = commands.ordinal();
            }
        }
    }

    public String[] getArgs() {
        return args;
    }

    public int getCommandNumber() {
        return commandNumber;
    }
}
