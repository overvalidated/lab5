package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Clear  {
    private Clear() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        targetCollection.clear();
    }
}
