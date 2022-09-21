package nia.chapter1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

public class NonBlockingExample {
    /**
     * Listing 1.1 Blocking I/O example
     */
    // TODO: write a Non blocking socket version
    public void serve(int portNumber) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(portNumber));
        Selector selector = Selector.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) { // 遍历所有已经准备就绪的事件
                if (key.isAcceptable()) { // 是一个ServerSocketChannel 有准备就绪的新链接
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) { // 是一个 SocketChannel 可以读取数据
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sc.read(buffer);
                    buffer.flip();
                    sc.write(buffer);
                }
            }
            keys.clear();
        }
    }

    private String processRequest(String request) {
        return "Processed";
    }
}
