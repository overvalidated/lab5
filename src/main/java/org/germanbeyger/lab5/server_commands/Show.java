package org.germanbeyger.lab5.server_commands;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class Show {
    private Show() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        String result = "";
        for (Ticket ticket : targetCollection)
            result += ticket.toString() + '\n';
        return result;
    }
}
