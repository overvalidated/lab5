package org.germanbeyger.lab5.server_commands;


import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Clear  {
    private Clear() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        targetCollection.clear();
        return "";
    }
}
