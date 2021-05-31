package org.germanbeyger.lab5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.rmi.NoSuchObjectException;
import java.util.Scanner;

import org.germanbeyger.lab5.client_commands.Commands;
import org.germanbeyger.lab5.datatypes.SendableCommand;

public class Client {
    public static SendableCommand executeCommand(Scanner stdInScanner) {
        String commandLine = null;
        commandLine = stdInScanner.nextLine();
        String[] commandArgs = commandLine.split(" ");
        try {
            return Commands.invokeCommand(commandArgs, stdInScanner);
        } catch (NoSuchObjectException ex) {
            System.out.println("This command doesn't exist. ");
        } catch (TransmissionInterrupted ex) {
            System.out.println("End of transmission... Closing client. ");
            System.exit(-1);
        }
        return null;
    }

    public static void main(String[] args) {
        args = new String[] {"localhost", "5000"};
        final int PORT = Integer.parseInt(args[1]);
        try (Scanner stdInScanner = new Scanner(System.in)) {
            while (true) {
                try (//Socket socket = new Socket(args[0], PORT);
                    DatagramSocket datagramSocket = new DatagramSocket(PORT+1)) {
                    // OutputStream out = socket.getOutputStream();
                    
                    // commandArgs[0] is a main command
                    // commangdArgs[1] is an argument.
                    ByteArrayOutputStream objectByteArray = new ByteArrayOutputStream();
                    ObjectOutputStream objStream = new ObjectOutputStream(objectByteArray);
                    SendableCommand command = executeCommand(stdInScanner);
                    if (command != null) {
                        objStream.writeObject(command);
                        byte[] commandBytes = objectByteArray.toByteArray();
                        datagramSocket.send(new DatagramPacket(commandBytes, commandBytes.length, new InetSocketAddress("localhost", PORT)));
                        System.out.println("Sent!");
                    }
                    
                    // byte[] responseBuffer = new byte[65508];
                    // DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
                    // datagramSocket.receive(response);

                } catch (UnknownHostException ex) {
                    System.out.println("The hostname is unknown. ");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Exception...");
                    System.exit(-1);
                }

            }
        } finally {} 
    }
}
