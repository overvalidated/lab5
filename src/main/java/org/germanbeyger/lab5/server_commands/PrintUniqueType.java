package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;
import java.util.TreeSet;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;
import org.germanbeyger.lab5.datatypes.TicketType;

public final class PrintUniqueType {
    private PrintUniqueType() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        TreeSet<TicketType> uniqueTypes = new TreeSet<>();
        for (Ticket ticket : targetCollection) 
            uniqueTypes.add(ticket.getType());
        System.out.printf("Unique types in collection: %s\n", uniqueTypes);
        
    }
}
