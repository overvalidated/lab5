package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class RemoveGreater{
    private RemoveGreater() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        targetCollection.removeIf(ticket -> ticket.compareTo(command.getTicket()) > 0);
        return "";
    }
}
