package nio.chapter2.customechoserver;

import org.junit.jupiter.api.Test;

class EchoClientTest {

    @Test
    void start() throws InterruptedException {
        EchoClient echoClient = new EchoClient("127.0.0.1", 8080);
        echoClient.start();
    }
}