package org.germanbeyger.lab5.commands;

import java.io.Serializable;
import java.util.Objects;

import org.germanbeyger.lab5.datatypes.Ticket;

/**
 * Class used for commands serialization
 */
public class SendableCommand implements Serializable {
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

    public String getCommandName() {
        return commandName;
    }

    public Ticket getTicket() {
        return ticket;
    }

    // public Command GetCommand
    @Override
    public String toString() {
        return "{" + " args='" + getArgs() + "'" + ", commandName='" + getCommandName() + "'" + ", ticket='"
                + getTicket() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SendableCommand)) {
            return false;
        }
        SendableCommand sendableCommand = (SendableCommand) o;
        return Objects.equals(args, sendableCommand.args) && Objects.equals(commandName, sendableCommand.commandName)
                && Objects.equals(ticket, sendableCommand.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(args, commandName, ticket);
    }
}
