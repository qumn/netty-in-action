package nio.chapter1;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class NonBlockingExampleTest {

    @Test
    void testServe() throws IOException {
        NonBlockingExample nonBlockingExample = new NonBlockingExample();
        nonBlockingExample.serve(8080);
    }
}