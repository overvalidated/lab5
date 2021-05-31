package org.germanbeyger.lab5.datatypes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class used for commands serialization
 */
public class SendableCommand implements Serializable {
    private final static long serialVersionUID = 1L;
    private String[] args;
    private String commandName;
    private Ticket ticket = null;
    private LinkedList<SendableCommand> commandList = null;

    public SendableCommand(String[] args, String commandName) {
        this.args = args;
        this.commandName = commandName;
    }

    public SendableCommand(String[] args, String commandName, Ticket ticket) {
        this(args, commandName);
        this.ticket = ticket;
    }

    public SendableCommand(String[] args, String commandName, LinkedList<SendableCommand> commandList) {
        this(args, commandName);
        this.commandList = commandList;
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

    public LinkedList<SendableCommand> getCommandList() {
        return commandList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SendableCommand)) {
            return false;
        }
        SendableCommand sendableCommand = (SendableCommand) o;
        return Objects.equals(args, sendableCommand.args) && Objects.equals(commandName, sendableCommand.commandName) && Objects.equals(ticket, sendableCommand.ticket) && Objects.equals(commandList, sendableCommand.commandList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(args, commandName, ticket, commandList);
    }

    @Override
    public String toString() {
        return "{" +
            " args='" + getArgs() + "'" +
            ", commandName='" + getCommandName() + "'" +
            ", ticket='" + getTicket() + "'" +
            ", commandList='" + getCommandList() + "'" +
            "}";
    }

}
