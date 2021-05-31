package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.server_commands.SendableCommand;

public final class RemoveById {
    private RemoveById() {}

    public static SendableCommand execute(String[] commandArgs, Scanner stdInScanner) {
        String id = FieldRequester.retrieveArgument(commandArgs, (input) -> input);
        return new SendableCommand(new String[] {}, "remove_by_id");
    }
}
