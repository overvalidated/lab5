package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.server_commands.SendableCommand;

public final class AddIfMax {
    private AddIfMax() {}

    public static SendableCommand execute(String[] args, Scanner stdInScanner) throws TransmissionInterrupted {
        return new SendableCommand(new String[] {}, "add_if_max", CLIObjectCreator.requestNewTicket(stdInScanner));
    }
}
