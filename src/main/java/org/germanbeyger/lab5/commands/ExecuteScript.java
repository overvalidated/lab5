package org.germanbeyger.lab5.commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NoSuchObjectException;
import java.util.Scanner;

import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class ExecuteScript {
    private ExecuteScript() {}

    public static void execute_script(String scriptPath, TargetCollection targetCollection, Scanner stdInScanner) {
        try (FileInputStream fstream = new FileInputStream(scriptPath)) {
            InputStreamReader inputStream = new InputStreamReader(fstream);
            BufferedReader bufStream = new BufferedReader(inputStream);
            FieldRequester.fromFile = true;

            String command = null;  
            while ((command = bufStream.readLine()) != null) { 
                if (command.equals("")) continue;
                String[] commandArgs = command.split(" "); 
                Commands.invokeCommand(commandArgs, targetCollection, new Scanner(bufStream));
            } 
        } catch (NoSuchObjectException e) {
            System.out.printf("%s Aborting further execution. \n", e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found.\nPrefer absolute path over relative.\n", scriptPath);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong while executing a script. \n");
        } finally {
            FieldRequester.fromFile = false;
        }
    }

    public static void execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        try {
            
            String scriptPath = FieldRequester.retrieveArgument(commandArgs, (input) -> input);
            execute_script(scriptPath, targetCollection, stdInScanner);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
