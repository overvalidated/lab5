package org.germanbeyger.lab5.client_commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NoSuchObjectException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.germanbeyger.lab5.TransmissionInterrupted;
import org.germanbeyger.lab5.cli.FieldRequester;
import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;


public final class ExecuteScript {
    private ExecuteScript() {}

    public static SendableCommand executeScript(String scriptPath) {
        LinkedList<SendableCommand> listOfCommands = new LinkedList<>();

        try (FileInputStream fstream = new FileInputStream(scriptPath);
            InputStreamReader inputStream = new InputStreamReader(fstream);
            BufferedReader bufStream = new BufferedReader(inputStream);
            Scanner fileScanner = new Scanner(bufStream)) { 

            String command = null;  
            while ((command = fileScanner.nextLine()) != null) { 
                if (command.equals("")) continue;
                String[] commandArgs = command.split(" "); 
                SendableCommand newCommand = Commands.invokeCommand(commandArgs, fileScanner);
                listOfCommands.add(newCommand);
            } 
        } catch (TransmissionInterrupted e) {
            System.out.printf("%s Aborting further execution. \n", e.getMessage());
        } catch (NoSuchElementException ex) {
            System.out.printf("No more lines.");
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found.\nPrefer absolute path over relative.\n", scriptPath);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong while executing a script.");
        } 

        return new SendableCommand(new String[]{}, "execute_script", listOfCommands);
    }
}
