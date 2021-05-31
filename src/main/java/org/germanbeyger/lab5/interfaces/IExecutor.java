package org.germanbeyger.lab5.interfaces;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public interface IExecutor {
    public String execute(SendableCommand command, TargetCollection targetCollection);
}
