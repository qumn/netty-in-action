package nio.chapter8.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Random;

/**
 * send a random int to cline
 */
public class RandomServer {
    public static void main(String[] args) throws InterruptedException {
        RandomServer randomServer = new RandomServer();
        randomServer.start();
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new RandomHandler());
            ChannelFuture f = bootstrap.bind(8081).sync();
            System.out.println(RandomServer.class.getName() +
                    " started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture();
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }

}


@ChannelHandler.Sharable
class RandomHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Random random = new Random();
        ctx.writeAndFlush(Unpooled.copiedBuffer(random.nextInt() + "", CharsetUtil.UTF_8));
    }
}