package cn.cat.netty.demo.aio.server;

import cn.cat.netty.demo.aio.ChannelAdapter;
import cn.cat.netty.demo.aio.ChannelHandler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class AioServerHandler extends ChannelAdapter {
    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("客户端连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("收到客户端消息：" + msg.toString());
        ctx.writeAndFlush("服务端回复：" + msg.toString());
    }
}
