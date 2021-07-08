package org.germanbeyger.lab5.server_commands;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class PrintDescending {
    private PrintDescending() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        return Show.execute(command, targetCollection);
    }
}
