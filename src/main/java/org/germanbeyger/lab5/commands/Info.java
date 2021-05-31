package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Info extends Executable {
    private Info() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        System.out.println(targetCollection.getInfoAboutCollection());
    }
}
