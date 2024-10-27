package cn.cat.netty.demo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        logger.info("有一客户端链接到本服务端");
        logger.info("链接报告IP:" + channel.localAddress().getHostString());
        logger.info("链接报告Port:" + channel.localAddress().getPort());
        logger.info("链接报告完毕");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("收到客户端消息:" + msg.toString());
        ctx.writeAndFlush("服务端回复: 你好，客户端！");
    }
}
