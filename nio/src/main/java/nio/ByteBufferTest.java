package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/qumn/a.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buf1 = ByteBuffer.allocate(10);
        ByteBuffer buf2 = ByteBuffer.allocate(20);
        ByteBuffer[] buffers = {buf1, buf2};
        channel.read(buffers);

        buf1.flip();
        buf2.flip();
        System.out.println("===================buf1=====================");
        while (buf1.hasRemaining()) {
            System.out.print((char)buf1.get());
        }
        System.out.println("===================buf2=====================");
        while (buf2.hasRemaining()) {
            System.out.print((char)buf2.get());
        }
    }
}
