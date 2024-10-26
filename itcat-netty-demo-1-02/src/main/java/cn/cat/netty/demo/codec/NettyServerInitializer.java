package cn.cat.netty.demo.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 解码器 基于换行符的解码器
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        // 解码器 字符串解码器
        pipeline.addLast(new StringDecoder(Charset.forName("GBK")));
        // 编码器 字符串编码器
        pipeline.addLast(new StringEncoder(Charset.forName("GBK")));
        // 自定义的处理器
        pipeline.addLast(new MyServerHandler());
    }
}
