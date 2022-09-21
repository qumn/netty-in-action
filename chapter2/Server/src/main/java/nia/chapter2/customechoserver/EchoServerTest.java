package nia.chapter2.customechoserver;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {

    @org.junit.jupiter.api.Test
    void start() throws InterruptedException {
        EchoServer echoServer = new EchoServer(8080);
        echoServer.start();
    }
}