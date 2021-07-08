package org.germanbeyger.lab5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Check if every required argument is passed. ");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception whiel parsing arguments. ");
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        
        final int PORT_SERVER = Integer.parseInt(args[1]);
        final int PORT_INCOM = Integer.parseInt(args[2]);
        try (Scanner stdInScanner = new Scanner(System.in)) {
            while (true) {
                try ( /* Socket socket = new Socket(args[0], PORT); */ DatagramSocket datagramSocket = new DatagramSocket(PORT_INCOM)) {
                    // OutputStream out = socket.getOutputStream();
                    
                    // commandArgs[0] is a main command
                    // commangdArgs[1] is an argument.
                    ByteArrayOutputStream objectByteArray = new ByteArrayOutputStream();
                    ObjectOutputStream objStream = new ObjectOutputStream(objectByteArray);
                    SendableCommand command = executeCommand(stdInScanner);
                    if (command != null) {
                        
                        objStream.writeObject(command);
                        byte[] commandBytes = objectByteArray.toByteArray();
                        datagramSocket.send(new DatagramPacket(commandBytes, commandBytes.length, new InetSocketAddress(args[0], PORT_SERVER)));
                        
                        //Response
                        byte[] responseBuffer = new byte[65508];
                        DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
                        datagramSocket.setSoTimeout(5000);
                        try {
                            datagramSocket.receive(response);
                            System.out.println(new String(response.getData(), 0, response.getLength()));
                        } catch (SocketTimeoutException ex) {
                            System.out.println("Server response timed out.");
                        }
                    }


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
