package com.common.io;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 基于netty的client
 *
 * @author subo
 * @create 2018-02-10 16:35
 **/
public class NettyEchoClient {

    private final String host;

    private final int port;

    private final int firstMessageSize;

    public NettyEchoClient(String host, int port, int firstMessageSize) {
        this.host = host;
        this.port = port;
        this.firstMessageSize = firstMessageSize;
    }

    public void run() throws Exception {
        // configure the client
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyEchoClientHandler(firstMessageSize));
                }
            });
            // start the client
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            // wait util the connection is closed
            channelFuture.channel().closeFuture().sync();
        } finally {
            // shut down the event loop to terminate all threads
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8080;
        int firstMessageSize = 256;
        new NettyEchoClient(host, port, firstMessageSize).run();
    }
 }
