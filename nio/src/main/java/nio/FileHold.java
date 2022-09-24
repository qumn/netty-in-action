package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileHold {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/qumn/c.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(11);
        buf.put("hello world".getBytes());
        buf.flip();
        channel.write(buf);
        long position = channel.position();
        channel.position(position + 10); // create a file hold
        buf.flip();
        buf.put("hello world".getBytes());
        buf.flip();
        channel.write(buf);
    }
}
