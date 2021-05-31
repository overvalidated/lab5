package org.germanbeyger.lab5.server_commands;

import java.rmi.NoSuchObjectException;
import java.util.LinkedList;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class ExecuteScript {
    private ExecuteScript() {
    }

    public static String executeScript(LinkedList<SendableCommand> commands, TargetCollection targetCollection) {
        for (SendableCommand command : commands) {
            try {
                Commands.invokeCommand(command, targetCollection);
            } catch (NoSuchObjectException e) {
                return String.format("%s Aborting further execution. \n", e.getMessage());
            }
        }
        return "";
    }
}
