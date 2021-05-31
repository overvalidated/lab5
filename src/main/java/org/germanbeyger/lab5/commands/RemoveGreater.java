package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class RemoveGreater extends Executable {
    private RemoveGreater() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        Ticket ticketToCompare = CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner);
        targetCollection.removeIf(ticket -> ticket.compareTo(ticketToCompare) > 0);
    }
}
