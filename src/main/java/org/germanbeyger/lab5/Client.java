package org.germanbeyger.lab5;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.germanbeyger.lab5.commands.SendableCommand;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try ( Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                Scanner stdInScanner = new Scanner(System.in)) {
            System.out.println("Connected!");
            SendableCommand command = new SendableCommand(new String[]{"asdf", "sdaf"}, "add");
            ObjectOutputStream objStream = new ObjectOutputStream(socket.getOutputStream());
            objStream.writeObject(command);
            System.out.println();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        } 
       
    }
}
