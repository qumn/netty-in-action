package nio.chapter8.custome.ShareEventLoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class cline {
    public static void main(String[] args) throws InterruptedException {
        SimpleChannelInboundHandler<ByteBuf> handler = new SimpleChannelInboundHandler<>() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("cline channel Active");
                ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
                CharsetUtil.UTF_8));
            }

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                System.out.println(msg.toString());
            }
        };
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1", 8080))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(handler);
                    }
                });
        ChannelFuture f = bootstrap.connect().sync();
        f.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}
