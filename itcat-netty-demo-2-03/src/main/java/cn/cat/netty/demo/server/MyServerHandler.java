package cn.cat.netty.demo.server;

import cn.cat.netty.demo.util.MsgUtil;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("Server channel active");
        ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(), "Welcome to server!"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server received: " + msg.getClass());
        System.out.println("Server received: " + JSON.toJSONString(msg));
    }
}
