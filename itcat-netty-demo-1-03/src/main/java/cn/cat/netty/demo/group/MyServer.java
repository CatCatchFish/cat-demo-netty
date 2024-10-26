package cn.cat.netty.demo.group;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {
    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    public static void main(String[] args) {
        new MyServer().bind(7397);
    }

    private void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128) // 非阻塞模式
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            logger.info("NettyServer bind port: " + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("NettyServer bind error", e);
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
