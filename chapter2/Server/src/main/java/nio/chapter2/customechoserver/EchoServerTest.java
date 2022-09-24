package nio.chapter2.customechoserver;

class EchoServerTest {

    @org.junit.jupiter.api.Test
    void start() throws InterruptedException {
        EchoServer echoServer = new EchoServer(8080);
        echoServer.start();
    }
}