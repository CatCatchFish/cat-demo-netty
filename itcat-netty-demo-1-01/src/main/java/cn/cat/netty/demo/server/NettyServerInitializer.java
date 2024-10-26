package cn.cat.netty.demo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        logger.info("init channel");
        logger.info("there is a new client connected");
        logger.info("client ip:{}", socketChannel.localAddress().getHostName());
        logger.info("client port:{}", socketChannel.localAddress().getPort());
        logger.info("channel initialized");
    }
}
