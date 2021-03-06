package org.germanbeyger.lab5;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.rmi.NoSuchObjectException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.server_commands.Commands;
import org.germanbeyger.lab5.server_commands.Save;

/**
 * Class responsible for running server
 */
public class Server {
    private static TargetCollection targetCollection;
    private static String collectionPath = "collection.xml";

    public static String execute_command(SendableCommand command) {
        try {
            return Commands.invokeCommand(command, targetCollection);
        } catch (NoSuchObjectException ex) {
            return "This command doesn't exist";
        }
    }

    public static void initializeCollection(String[] args) {
        collectionPath = args[1];
        targetCollection = XMLCollectionProcessor.load(collectionPath);
        if (targetCollection != null) {
            if (!targetCollection.verify()) {
                System.out.println("Loaded collection is corrupted. ");
                targetCollection = null;
            }
        }
        // если не проходим верификацию - нужна новая коллекция
        File file = new File(collectionPath);
        // Если коллекция не загружена - запрашиваем создание новой
        if (targetCollection == null) {
            System.out.println("Reinitializing the collection. ");
            if (file.canWrite() & file.canRead()) {
                targetCollection = new TargetCollection();
            } else {
                System.out.println("Not enough rights to access collection.");
                System.exit(-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // if port and path not provided then stop execution
        if (args.length != 2) {
            System.out.printf("Wrong number of arguments: expected 2 (port and collection path), got %d\n",
                    args.length);
            return;
        }

        // loading collection
        initializeCollection(args);

        // saving on exit
        Thread savingCollectionHook = new Thread(() -> Save
                .execute(new SendableCommand(new String[] { "save", collectionPath }, "save"), targetCollection));
        Runtime.getRuntime().addShutdownHook(savingCollectionHook);

        // creating thread for keyboard processing
        Thread waitingKeyboard = new Thread(new Runnable() {
            public void run() {
                try (Scanner sc = new Scanner(System.in)) {
                    String nextInput = "";
                    while (true) {
                        if (sc.hasNextLine()) {
                            nextInput = sc.nextLine();
                        }
                        switch (nextInput) {
                            case "exit":
                                System.exit(0); // saving with shutdown hook
                            case "save":
                                Save.execute(new SendableCommand(new String[] { "save", collectionPath }, "save"),
                                        targetCollection);
                        }
                    }
                }
            }
        });

        // port to listen to
        final int PORT = Integer.parseInt(args[0]);
        System.out.println("Used port is: " + PORT);

        // binding port for udp and setting non-blocking mode
        DatagramChannel udpChannel = DatagramChannel.open();
        udpChannel.socket().bind(new InetSocketAddress(PORT));
        udpChannel.configureBlocking(false);

        // Listening to keyboard...
        waitingKeyboard.start();
        // ServerSocketChannel sChannel;
        // try-with-resources, using selector and 
        try (Selector connectionSelector = Selector.open()) {
            udpChannel.register(connectionSelector, SelectionKey.OP_READ);
            // sChannel = ServerSocketChannel.open();
            // sChannel.socket().bind(new InetSocketAddress(PORT));
            // sChannel.configureBlocking(false);
            // sChannel.register(connectionSelector, SelectionKey.OP_ACCEPT);

            while (true) {
                // blocking until at least one channel is ready
                int readyChannels = connectionSelector.selectNow();

                if (readyChannels == 0)
                    continue;

                Set<SelectionKey> selectedKeys = connectionSelector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                // new tcp connection
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    // if (key.isAcceptable()) {
                    // System.out.println("Connection in...");
                    // asdemfnaskdfjnaskjfnsjdflaksdlfkjServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    // SocketChannel socketChannel = serverChannel.accept();
                    // System.out.println("Connection accepted!");

                    // if (socketChannel != null){
                    // socketChannel.configureBlocking(false);
                    // socketChannel.register(connectionSelector, SelectionKey.OP_READ);
                    // }
                    // }

                    if (key.isReadable()) {
                        try {
                            // SocketChannel socketChannel = (SocketChannel) key.channel();
                            DatagramChannel socketChannel = (DatagramChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(65508);
                            SocketAddress sch = socketChannel.receive(buffer);
                            while (sch == null) {
                                sch = socketChannel.receive(buffer);
                            }

                            // int bytesRead = socketChannel.read(buffer);
                            // if (bytesRead == -1) continue;
                            // while (bytesRead != -1) {
                            // bytesRead = socketChannel.read(buffer);
                            // }
                            try {
                                ByteArrayInputStream bytesStream = new ByteArrayInputStream(buffer.array());
                                ObjectInputStream objStream = new ObjectInputStream(bytesStream);
                                Object deserialized = objStream.readObject();
                                if (deserialized instanceof SendableCommand) {
                                    SendableCommand command = (SendableCommand) deserialized;
                                    String response = execute_command(command);
                                    System.out.println(response);
                                    ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
                                    socketChannel.send(responseBuffer, sch);
                                    // command processing
                                    // System.exit(-1);
                                }
                            } catch (ClassNotFoundException ex) {
                                ByteBuffer responseBuffer = ByteBuffer.wrap("Can't deserialize command.".getBytes());
                                socketChannel.send(responseBuffer, sch);
                            } catch (StreamCorruptedException ex) {
                                ByteBuffer responseBuffer = ByteBuffer
                                        .wrap("Error while deserializing command".getBytes());
                                socketChannel.send(responseBuffer, sch);
                            } catch (EOFException ex) {
                                ByteBuffer responseBuffer = ByteBuffer.wrap("Request is too big. ".getBytes());
                                socketChannel.send(responseBuffer, sch);
                            } catch (Exception ex) {
                                ByteBuffer responseBuffer = ByteBuffer.wrap("Unexpected exception ".getBytes());
                                socketChannel.send(responseBuffer, sch);
                            }

                        } catch (ClassCastException ex) {
                            ex.printStackTrace(); // remove it later
                            // System.exit(-1);
                        }
                    }
                }
            }
        }
    }
}
