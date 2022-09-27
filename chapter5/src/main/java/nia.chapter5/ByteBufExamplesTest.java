package nia.chapter5;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledDirectByteBuf;
import nio.chapter5.ByteBufExamples;

import static org.junit.jupiter.api.Assertions.*;

class ByteBufExamplesTest {

    @org.junit.jupiter.api.Test
    void byteBufSlice() {
        ByteBufExamples byteBufExamples = new ByteBufExamples();
        byteBufExamples.byteBufSlice();
    }

    @org.junit.jupiter.api.Test
    void byteBufCopy() {
        ByteBufExamples byteBufExamples = new ByteBufExamples();
        byteBufExamples.byteBufCopy();
    }
    void test(){
        UnpooledByteBufAllocator.DEFAULT.heapBuffer();
    }
}