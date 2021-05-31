package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;
import org.germanbeyger.lab5.server_commands.SendableCommand;

public final class Update {
    private Update() {}

    public static SendableCommand execute(String[] commandArgs, Scanner stdInScanner) {
        return new SendableCommand(new String[] {}, "update");
    }
}
