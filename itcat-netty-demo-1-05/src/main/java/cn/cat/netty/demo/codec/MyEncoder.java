package cn.cat.netty.demo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义编码器
 * 协议格式：
 * 1. 起始位：0x02
 * 2. 数据长度：int 类型，占 4 字节
 * 3. 数据内容：byte 数组
 * 4. 结束位：0x03
 */
public class MyEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        String msg = in.toString();
        byte[] bytes = msg.getBytes();

        byte[] send = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, send, 1, bytes.length);
        send[0] = 0x02;
        send[send.length - 1] = 0x03;

        out.writeInt(send.length);
        out.writeBytes(send);
    }
}
