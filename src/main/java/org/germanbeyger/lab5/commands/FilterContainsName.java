package org.germanbeyger.lab5.commands;

import java.util.LinkedList;
import java.util.Scanner;

import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class FilterContainsName {
    private FilterContainsName() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        try {
            String name = FieldRequester.retrieveArgument(commandArgs, (input) -> input);
            LinkedList<Ticket> filteredCollection = targetCollection.filter(input -> input.getName().equals(name));
            for (Ticket ticket : filteredCollection)
                System.out.println(ticket);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
