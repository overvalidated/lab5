package org.germanbeyger.lab5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


import org.germanbeyger.lab5.commands.SendableCommand;

/**
 * Class responsible for running server
 */
public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = Integer.parseInt(args[0]);
        System.out.println(PORT);

        ServerSocketChannel sChannel;
        Selector connectionSelector = Selector.open();

        sChannel = ServerSocketChannel.open();
        sChannel.socket().bind(new InetSocketAddress("0.0.0.0", 0));
        sChannel.configureBlocking(false);
        sChannel.register(connectionSelector, SelectionKey.OP_ACCEPT);

        while (true) {
            // blocking until at least one channel is ready
            int readyChannels = connectionSelector.selectNow();

            if(readyChannels == 0) continue;

            Set<SelectionKey> selectedKeys = connectionSelector.selectedKeys(); 
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
             
            // new tcp connection
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    
                    if (socketChannel != null){
                        socketChannel.configureBlocking(false);
                        socketChannel.register(connectionSelector, SelectionKey.OP_READ);
                    }
                }

                if (key.isReadable()) {
                    try {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(8192);
                        int bytesRead = socketChannel.read(buffer);
                        while (bytesRead != -1) {
                            bytesRead = socketChannel.read(buffer);
                        }
                        if (buffer.position() == 0) continue;
                        try {
                            ByteArrayInputStream bytesStream = new ByteArrayInputStream(buffer.array());
                            ObjectInputStream objStream = new ObjectInputStream(bytesStream);
                            Object deserialized = objStream.readObject();
                            if (deserialized instanceof SendableCommand) {
                                SendableCommand command = (SendableCommand) deserialized;
                                System.out.println(command);
                                System.out.printf("Ticket verification: %b\n", command.getTicket().verify());
                                // System.exit(-1);
                            }
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace(); // remove it later
                            // System.exit(-1);
                        } catch (StreamCorruptedException ex) {
                            ex.printStackTrace();
                            // System.exit(-1);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            // System.exit(-1);
                        }

                    }
                    catch (ClassCastException ex) {
                        ex.printStackTrace(); // remove it later
                        // System.exit(-1);
                    }
                }
            }
        }
    }
}
