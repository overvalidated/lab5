package org.germanbeyger.lab5.server_commands;

import java.util.NoSuchElementException;

import org.germanbeyger.lab5.datatypes.TargetCollection;


public final class Update {
    private Update() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        String result = "";
        try {
            targetCollection.update(Integer.parseInt(command.getArgs()[0]), 
                    command.getTicket());
        } catch (IllegalArgumentException e) {
            result = "The argument to this command must be an integer. ";
        } catch (NoSuchElementException e) {
            result = "Index not found in collection. ";
        }
        return result;
        
    }
}
