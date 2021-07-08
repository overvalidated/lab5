package org.germanbeyger.lab5.server_commands;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Exit {
    private Exit() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        Save.execute(command, targetCollection);
        System.exit(0);
        return "";
    }
}
