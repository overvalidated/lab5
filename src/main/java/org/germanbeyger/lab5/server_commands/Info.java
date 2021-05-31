package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Info {
    private Info() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        System.out.println(targetCollection.getInfoAboutCollection());
    }
}
