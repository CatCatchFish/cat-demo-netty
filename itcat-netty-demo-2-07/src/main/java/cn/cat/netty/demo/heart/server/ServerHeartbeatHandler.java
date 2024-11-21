package cn.cat.netty.demo.heart.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ServerHeartbeatHandler extends IdleStateHandler {

    // 设置读取事件为10s
    // 如果10s没有读事件发生 就会触发下面的IdleStateEvent
    private static final int READER_IDLE_TIME = 10;

    public ServerHeartbeatHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt)  {
        // 指定时间内没有读事件发送 就会触发 IdleState.READER_IDLE 类型事件
        // 我们就可以对该连接进行处理 这里是直接关闭
        if (evt.state() == IdleState.READER_IDLE) {
            System.out.println("客户端长时间没有读消息，关闭连接");
            ctx.channel().close();
        }
    }
}
