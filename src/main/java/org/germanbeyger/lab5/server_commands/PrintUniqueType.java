package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;
import java.util.TreeSet;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;
import org.germanbeyger.lab5.datatypes.TicketType;

public final class PrintUniqueType {
    private PrintUniqueType() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        TreeSet<TicketType> uniqueTypes = new TreeSet<>();
        for (Ticket ticket : targetCollection) 
            uniqueTypes.add(ticket.getType());
        return String.format("Unique types in collection: %s\n", uniqueTypes);
    }
}
