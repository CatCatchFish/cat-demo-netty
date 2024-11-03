package cn.cat.netty.demo.server;

import cn.cat.netty.demo.codec.ObjDecoder;
import cn.cat.netty.demo.codec.ObjEncoder;
import cn.cat.netty.demo.domain.FileTransferProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        // 添加自己的处理器
        channel.pipeline().addLast(new MyServerHandler());
    }
}
