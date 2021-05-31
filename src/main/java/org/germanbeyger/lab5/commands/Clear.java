package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Clear extends Executable {
    private Clear() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        targetCollection.clear();
    }
}
