package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class AddIfMax {
    private AddIfMax() {}

    public static void execute(String[] args, TargetCollection targetCollection, Scanner stdInScanner) {
        targetCollection.addIfMax(
            CLIObjectCreator.requestNewTicket(targetCollection.getNextId(), stdInScanner)
        );
    }
}
