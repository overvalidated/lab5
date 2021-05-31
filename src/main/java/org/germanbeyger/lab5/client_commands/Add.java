package org.germanbeyger.lab5.client_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.cli.CLIObjectCreator;
import org.germanbeyger.lab5.datatypes.SendableCommand;

public final class Add  {
    private Add() {}

    public static SendableCommand execute(String[] commandArgs,  Scanner stdInScanner) throws TransmissionInterrupted {
        return new SendableCommand(new String[] {}, "add", CLIObjectCreator.requestNewTicket(stdInScanner));
    }
    /**
     * This overload allows for sending command to server.
     * It takes a connection to write to. And sends a serialized version of itself. 
     */
}
