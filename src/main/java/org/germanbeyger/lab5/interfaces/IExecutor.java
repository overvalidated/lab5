package org.germanbeyger.lab5.interfaces;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public interface IExecutor {
    public void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner);
}
