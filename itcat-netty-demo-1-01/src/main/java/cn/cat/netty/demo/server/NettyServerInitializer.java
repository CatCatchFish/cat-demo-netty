package cn.cat.netty.demo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        logger.info("init channel");
        logger.info("there is a new client connected");
        logger.info("client ip:{}", channel.localAddress().getHostName());
        logger.info("client port:{}", channel.localAddress().getPort());
        logger.info("channel initialized");

        channel.pipeline().addLast(new MyServerHandler());
    }
}
