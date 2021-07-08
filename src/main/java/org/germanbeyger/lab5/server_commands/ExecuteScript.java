package org.germanbeyger.lab5.server_commands;

import java.rmi.NoSuchObjectException;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class ExecuteScript {
    private ExecuteScript() {
    }

    public static String execute(SendableCommand commands, TargetCollection targetCollection) {
        String result = "";
        for (SendableCommand command : commands.getCommandList()) {
            try {
                result += Commands.invokeCommand(command, targetCollection);
            } catch (NoSuchObjectException e) {
                result += String.format("%s Aborting further execution. \n", e.getMessage());
            }
        }
        return result;
    }
}
