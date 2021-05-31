package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class Update {
    private Update() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        try {
            int idx = FieldRequester.retrieveArgument(commandArgs, Integer::parseInt);
            Ticket replacementTicket = CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner);
            targetCollection.update(idx, replacementTicket);
        } catch (IllegalArgumentException e) {
            System.out.println("The argument to this command must be an integer. ");
        }
    }
}
