package org.germanbeyger.lab5.server_commands;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class History {
    private History() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        return targetCollection.getStringifiedHistory();
    }
}
