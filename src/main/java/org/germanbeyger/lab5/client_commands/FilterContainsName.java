package org.germanbeyger.lab5.client_commands;

import java.util.LinkedList;
import java.util.Scanner;

import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class FilterContainsName {
    private FilterContainsName() {}

    public static SendableCommand execute(String[] commandArgs, Scanner stdInScanner) {
        FieldRequester.retrieveArgument(commandArgs, (input) -> input);
        return new SendableCommand(commandArgs, "filter_contains_name");
    }
    
}
