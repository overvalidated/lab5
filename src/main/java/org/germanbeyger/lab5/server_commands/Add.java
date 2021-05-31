package org.germanbeyger.lab5.server_commands;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Add  {
    private Add() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        targetCollection.add(command.getTicket());
        return "";
    }
}
