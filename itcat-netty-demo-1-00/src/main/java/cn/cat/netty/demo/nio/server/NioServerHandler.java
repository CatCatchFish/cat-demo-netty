package cn.cat.netty.demo.nio.server;

import cn.cat.netty.demo.nio.ChannelAdapter;
import cn.cat.netty.demo.nio.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NioServerHandler extends ChannelAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NioServerHandler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public NioServerHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            logger.info("连接日志LocalAddress:{}", ctx.channel().getLocalAddress());
            ctx.writeAndFlush("欢迎来到 NIO - 服务端\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        logger.info("{}，接收到客户端消息：{}", dateFormat.format(new Date()), msg);
        ctx.writeAndFlush("服务端收到消息\r\n");
    }
}
