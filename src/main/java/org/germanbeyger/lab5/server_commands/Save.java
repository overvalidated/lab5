package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.XMLCollectionProcessor;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Save {
    private Save() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        XMLCollectionProcessor.save(targetCollection, commandArgs[1]);
    }
    
}
