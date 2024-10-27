package cn.cat.netty.demo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class MyClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MyClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        logger.info("连接成功，IP：" + channel.localAddress().getHostString());
        logger.info("连接成功，Port：" + channel.localAddress().getPort());

        String message = "通知服务器连接成功，ip:" + channel.localAddress().getHostString() + ",port:" + channel.localAddress().getPort()
                + "\r\n";
        channel.writeAndFlush(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("连接断开,{}", ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息，此处不需要自己进行解码
        logger.info("客户端收到消息：{}", msg);
        //通知客户端链消息发送成功
        String str = "客户端收到：" + new Date() + " " + msg + "\r\n";
        ctx.writeAndFlush(str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error("发生异常，关闭连接", cause);
    }
}
