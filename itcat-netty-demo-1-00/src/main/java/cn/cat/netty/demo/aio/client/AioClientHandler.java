package cn.cat.netty.demo.aio.client;

import cn.cat.netty.demo.aio.ChannelAdapter;
import cn.cat.netty.demo.aio.ChannelHandler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class AioClientHandler extends ChannelAdapter {
    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("client connected" + ctx.channel().getRemoteAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("client received: " + msg.toString());
        ctx.writeAndFlush("hello server");
    }
}
