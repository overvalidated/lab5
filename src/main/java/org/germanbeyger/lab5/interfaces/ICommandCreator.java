package org.germanbeyger.lab5.interfaces;

import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.datatypes.SendableCommand;

public interface ICommandCreator {
    public SendableCommand execute(String[] commandArgs, Scanner stdInScanner) throws TransmissionInterrupted;
}
