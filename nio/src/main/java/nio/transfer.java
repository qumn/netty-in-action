package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class transfer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/qumn/a.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("/Users/qumn/b.txt", "rw");
        FileChannel toChannel = bFile.getChannel();

        toChannel.transferFrom(fromChannel, 0, fromChannel.size());
    }
}
