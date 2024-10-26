package cn.cat.netty.demo.group;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelHandler.channelGroup.add(ctx.channel());
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        logger.info("客户端连接成功，地址为：{}，端口为：{}", socketChannel.remoteAddress().getAddress().getHostAddress(), socketChannel.remoteAddress().getPort());
        String str = "欢迎访问Netty服务端";
        ctx.writeAndFlush(str);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        logger.info("客户端连接断开，地址为：{}，端口为：{}", socketChannel.remoteAddress().getAddress().getHostAddress(), socketChannel.remoteAddress().getPort());
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        logger.info("收到客户端消息：{}", message);
        String str = "服务端收到：" + message;
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error("发生异常，关闭连接", cause);
    }
}
