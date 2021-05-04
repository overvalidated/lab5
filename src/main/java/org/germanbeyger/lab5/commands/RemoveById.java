package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class RemoveById {
    private RemoveById() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        try {
            int idx = FieldRequester.retrieveArgument(commandArgs, Integer::parseInt);
            targetCollection.remove(idx);
        } catch (IllegalArgumentException e) {
            System.out.println("The argument to this command must be an integer. ");
        }
    }
}
