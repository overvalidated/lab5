package org.germanbeyger.lab5.commands;

import java.io.Serializable;

import org.germanbeyger.lab5.interfaces.IExecutor;


/**
 * Class used for commands serialization
 */
public abstract class SendableCommand implements IExecutor, Serializable {
    private final static long serialVersionUID = 1L;
    private String[] args;
    private Command commandNumber;

    public SendableCommand(String[] args, final String commandName) {
        this.args = args;
        for (Commands commands : Commands.values()) {
            // todo add command creation
        }
    }

    public String[] getArgs() {
        return args;
    }

    // public Command GetCommand

}