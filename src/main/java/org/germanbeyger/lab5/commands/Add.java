package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Add {
    private Add() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        targetCollection.add(
            CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner)
        );
    }
}
