package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.datatypes.SendableCommand;

public final class RemoveGreater{
    private RemoveGreater() {}

    public static SendableCommand execute(String[] commandArgs, Scanner stdInScanner) throws TransmissionInterrupted {
        return new SendableCommand(new String[] {}, "remove_greater", 
            CLIObjectCreator.requestNewTicket(stdInScanner));
    }
}
