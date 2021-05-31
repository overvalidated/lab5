package org.germanbeyger.lab5;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

import org.germanbeyger.lab5.server_commands.SendableCommand;

public class Client {
    public static void main(String[] args) {
        System.out.println("Attempting to connect to server...");
        try ( Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                Scanner stdInScanner = new Scanner(System.in)) {
            
            OutputStream out = socket.getOutputStream();
            System.out.println("Connected!");

            String nextCommand = null;
            while (true) {
                nextCommand = stdInScanner.nextLine();
                String[] commandArgs = nextCommand.split(" ");
                // commandArgs[0] is a main command
                // commangdArgs[1] is an argument.
                SendableCommand command = new SendableCommand(Arrays.copyOfRange(commandArgs, 1, commandArgs.length), commandArgs[0]);
                ObjectOutputStream objStream = new ObjectOutputStream(out);
                objStream.writeObject(command);
                out.flush();
            }
            // ObjectOutputStream objStream = new ObjectOutputStream(out);
            // objStream.writeObject(command);
            // BufferedOutputStream bufStream = new BufferedOutputStream(socket.getOutputStream());
            
            
            
        } catch (UnknownHostException ex) {
            System.out.println("The hostname is unknown. ");
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Exception...");
        } 
       
    }
}
