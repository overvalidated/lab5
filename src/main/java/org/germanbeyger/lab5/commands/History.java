package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class History {
    private History() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        System.out.print(targetCollection.getStringifiedHistory());
    }
}
