package nio.chapter8.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * invoke remote server then return the message to cline
 */
public class InvokeRemoteServer {
    public static void main(String[] args) {
        InvokeRemoteServer invokeRemoteServer = new InvokeRemoteServer();
        invokeRemoteServer.start();
    }

    public void start() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new InvokeRemoteHandler())
                    .localAddress(new InetSocketAddress(8082));
            ChannelFuture sync = bootstrap.bind().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }
}

class InvokeRemoteHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(ctx.channel().eventLoop())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext clineCtx, Object msg) throws Exception {
                        ctx.writeAndFlush(msg); // send the response of remote server to the cline
                    }
                })
                .remoteAddress("127.0.0.1", 8081);
        ChannelFuture f = bootstrap.connect();
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("connected to remote random server");
            }
        });

    }
}
