package org.germanbeyger.lab5.commands;

import java.io.Serializable;

import org.germanbeyger.lab5.datatypes.Ticket;
import org.germanbeyger.lab5.interfaces.IExecutor;


/**
 * Class used for commands serialization
 */
public abstract class SendableCommand implements IExecutor, Serializable {
    private final static long serialVersionUID = 1L;
    private String[] args;
    private String commandName;
    private Ticket ticket = null;

    public SendableCommand(String[] args, String commandName) {
        this.args = args;
        this.commandName = commandName;
    }

    public SendableCommand(String[] args, String commandName, Ticket ticket) {
        this(args, commandName);
        this.ticket = ticket;
    }

    public String[] getArgs() {
        return args;
    }

    public String getCommand() {
        return commandName;
    }

    // public Command GetCommand

}
