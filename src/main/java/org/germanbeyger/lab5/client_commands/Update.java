package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.SendableCommand;

public final class Update {
    private Update() {}

    public static SendableCommand execute(String[] commandArgs, Scanner stdInScanner) throws TransmissionInterrupted {
        Integer idx = FieldRequester.retrieveArgument(commandArgs, Integer::parseInt);
        if (idx < 1) {
            System.out.println("Index must be positive. ");
            return null;
        }
        return new SendableCommand(commandArgs, "update", 
            CLIObjectCreator.requestNewTicket(stdInScanner));
    }
}
