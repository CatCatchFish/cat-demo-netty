package cn.cat.netty.demo.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码器和编码器
        pipeline.addLast(new StringDecoder(Charset.forName("GBK")));
        pipeline.addLast(new StringEncoder(Charset.forName("GBK")));
        pipeline.addLast(new MyClientHandler());
    }
}
