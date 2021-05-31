package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class PrintDescending extends Executable {
    private PrintDescending() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        for (Ticket ticket : targetCollection.getSortedCollection()) 
            System.out.println(ticket);
    }
}
