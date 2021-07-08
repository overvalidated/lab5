package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.SendableCommand;

public final class RemoveById {
    private RemoveById() {}

    public static SendableCommand execute(String[] commandArgs, Scanner stdInScanner) {
        Integer id = FieldRequester.retrieveArgument(commandArgs, Integer::parseInt);
        if (id < 1) {
            System.out.println("Id must be positive.");
            return null;
        }
        return new SendableCommand(commandArgs, "remove_by_id");
    }
}
