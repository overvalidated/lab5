package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class Show extends Executable {
    private Show() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        for (Ticket ticket : targetCollection)
            System.out.println(ticket);
    }
}
