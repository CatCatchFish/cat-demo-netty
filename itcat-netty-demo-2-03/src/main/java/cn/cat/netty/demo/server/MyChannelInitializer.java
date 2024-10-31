package cn.cat.netty.demo.server;

import cn.cat.netty.demo.codec.ObjDecoder;
import cn.cat.netty.demo.codec.ObjEncoder;
import cn.cat.netty.demo.domain.MsgInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ObjEncoder(MsgInfo.class));
        pipeline.addLast(new ObjDecoder(MsgInfo.class));
        pipeline.addLast(new MyServerHandler());
    }
}
