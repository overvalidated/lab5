package org.germanbeyger.lab5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
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
            // blocking until atleast one channel is ready
            connectionSelector.select();
            Set<SelectionKey> selectedKeys = connectionSelector.selectedKeys();  

            for (SelectionKey key : selectedKeys) {
                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel connectionSocket = serverChannel.accept();

                    if (connectionSocket != null){
                        connectionSocket.configureBlocking(false);
                        connectionSocket.register(connectionSelector, SelectionKey.OP_READ);
                    }
                }

                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // socketChannel.read();
                }
            }
        }
    }
}
