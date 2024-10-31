package cn.cat.netty.demo.client;

import cn.cat.netty.demo.util.MsgUtil;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("Connected to server: " + channel.localAddress().getHostString());
        System.out.println("Connected to server port: " + channel.localAddress().getPort());
        String message = "Hello, Server!";
        ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(), message));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 不需要手动解码，在Initializer中配置了对应的解码器
        System.out.println("Received data from server: " + msg.getClass());
        System.out.println("Received data from server: " + JSON.toJSONString(msg));
    }
}
