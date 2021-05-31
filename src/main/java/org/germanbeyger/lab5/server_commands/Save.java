package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.XMLCollectionProcessor;
import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Save {
    private Save() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        XMLCollectionProcessor.save(targetCollection, command.getArgs()[1]);
        return "";
    }
    
}
