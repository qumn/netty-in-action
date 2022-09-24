package nio;

import java.io.IOException;
import java.nio.channels.Selector;

public class SelectorTest {
    public static void main(String[] args) throws IOException {
        Selector open = Selector.open();
    }
}
