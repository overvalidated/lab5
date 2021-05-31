package org.germanbeyger.lab5.server_commands;

import java.util.NoSuchElementException;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class RemoveById {
    private RemoveById() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        String result = "";
        try {
            targetCollection.remove(Integer.parseInt(command.getArgs()[0]));
        } catch (IllegalArgumentException e) {
            result = "The argument to this command must be an integer. ";
        } catch (NoSuchElementException e) {
            result = "Index not found in collection. ";
        }
        return result;

    }
}
