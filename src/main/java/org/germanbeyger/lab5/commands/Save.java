package org.germanbeyger.lab5.commands;

import java.util.Scanner;

import org.germanbeyger.lab5.XMLCollectionProcessor;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Save extends Executable {
    private Save() {}

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        XMLCollectionProcessor.save(targetCollection, commandArgs[1]);
    }
    
}
