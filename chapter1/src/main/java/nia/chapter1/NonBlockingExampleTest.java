package nia.chapter1;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NonBlockingExampleTest {

    @Test
    void testServe() throws IOException {
        NonBlockingExample nonBlockingExample = new NonBlockingExample();
        nonBlockingExample.serve(8080);
    }
}