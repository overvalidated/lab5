package org.germanbeyger.lab5.interfaces;

import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.server_commands.SendableCommand;

public interface IExecutor {
    public String execute(SendableCommand command, TargetCollection targetCollection);
}
