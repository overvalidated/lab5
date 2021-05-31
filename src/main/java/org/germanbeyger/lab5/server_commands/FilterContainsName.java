package org.germanbeyger.lab5.server_commands;

import java.util.LinkedList;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class FilterContainsName {
    private FilterContainsName() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        String result = "";
        LinkedList<Ticket> filteredCollection = 
            targetCollection.filter(input -> input.getName().equals(command.getArgs()[0]));
        for (Ticket ticket : filteredCollection)
            result += ticket.toString() + "\n";
        return result;
    }
    
}
