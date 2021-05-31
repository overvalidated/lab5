package org.germanbeyger.lab5;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.germanbeyger.lab5.commands.SendableCommand;

public class Client {
    public static void main(String[] args) {
        System.out.println("Attempting to connect to server...");
        try ( Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                Scanner stdInScanner = new Scanner(System.in)) {
            System.out.println("Connected!");

            SendableCommand command = new SendableCommand(new String[]{"asdf"}, "asdf");
            ObjectOutputStream objStream = new ObjectOutputStream(socket.getOutputStream());
            objStream.writeObject(command);
            
        } catch (UnknownHostException ex) {
            System.out.println("The hostname is unknown. ");
        } 
        catch (IOException ex) {
            System.out.println("Exception...");
        } 
       
    }
}
