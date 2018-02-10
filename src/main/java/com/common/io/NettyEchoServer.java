package com.common.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 基于netty的server
 *
 * @author subo
 * @create 2018-02-10 16:06
 **/
public class NettyEchoServer {

    private final int port;

    public NettyEchoServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // configure the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, wokerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyEchoServerHandler());
                }
            });
            // start the server
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // wait until the server socket is closed
            channelFuture.channel().closeFuture().sync();
        } finally {
            // shut down all event loops to terminate all threads
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyEchoServer(8080).run();
    }
}
