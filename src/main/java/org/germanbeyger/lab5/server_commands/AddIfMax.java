package org.germanbeyger.lab5.server_commands;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class AddIfMax {
    private AddIfMax() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        targetCollection.addIfMax(command.getTicket());
        return "";
    }
}
