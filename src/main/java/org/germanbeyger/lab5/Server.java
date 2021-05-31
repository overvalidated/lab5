package org.germanbeyger.lab5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Class responsible for running server
 */
public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = 34223;

        ServerSocketChannel sChannel;
        Selector connectionSelector = Selector.open();

        sChannel = ServerSocketChannel.open();
        sChannel.socket().bind(new InetSocketAddress(PORT));
        sChannel.configureBlocking(false);
        sChannel.register(connectionSelector, SelectionKey.OP_ACCEPT);

        while (true) {
            // blocking until at least one channel is ready
            connectionSelector.select();
            Set<SelectionKey> selectedKeys = connectionSelector.selectedKeys();  

            // new tcp connection
            for (SelectionKey key : selectedKeys) {
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
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = socketChannel.read(buffer);

                        while (bytesRead != -1) {
                            buffer 
                            int bytesRead = socketChannel.read(buffer);
                        }
                    }
                    catch (ClassCastException ex) {
                        ex.printStackTrace(); // remove it later
                    }
                }
            }
        }
    }
}
