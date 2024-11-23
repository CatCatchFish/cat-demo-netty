package cn.cat.netty.es.socket.server;

import cn.cat.netty.es.domain.TransportProtocol;
import cn.cat.netty.es.socket.codec.ObjDecoder;
import cn.cat.netty.es.socket.codec.ObjEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Resource
    private MyServerHandler myServerHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(TransportProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(TransportProtocol.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(myServerHandler);
    }
}
