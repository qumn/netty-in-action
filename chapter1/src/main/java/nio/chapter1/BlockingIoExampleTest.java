package nio.chapter1;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class BlockingIoExampleTest {

    @Test
    void serveTest() throws IOException {
        BlockingIoExample blockingIoExample = new BlockingIoExample();
        blockingIoExample.serve(8080);
    }
}