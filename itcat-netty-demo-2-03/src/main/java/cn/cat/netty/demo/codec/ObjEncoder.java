package cn.cat.netty.demo.codec;

import cn.cat.netty.demo.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjEncoder extends MessageToByteEncoder {
    private final Class<?> clazz;

    public ObjEncoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        if (clazz.isInstance(in)) {
            byte[] data = SerializationUtil.serialize(in);
            // 写入数据长度
            out.writeInt(data.length);
            // 写入数据字节
            out.writeBytes(data);
        }
    }
}
