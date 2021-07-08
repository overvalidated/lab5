package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Clear  {
    private Clear() {}

    public static SendableCommand execute(String[] args, Scanner stdInScanner) {
        return new SendableCommand(args, "clear");
    }
}
