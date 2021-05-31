package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;
import org.germanbeyger.lab5.server_commands.SendableCommand;

public final class Show {
    private Show() {}

    public static SendableCommand execute(Object... args) {
        return new SendableCommand(new String[] {}, "show");
    }
}
