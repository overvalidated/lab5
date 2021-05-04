package org.germanbeyger.lab5.interfaces;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

@FunctionalInterface
public interface IExecutor {
    void execute(String[] args, TargetCollection targetCollection, Scanner stdInScanner);
}
