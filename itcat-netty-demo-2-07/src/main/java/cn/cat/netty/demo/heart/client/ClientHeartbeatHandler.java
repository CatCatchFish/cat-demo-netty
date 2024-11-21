package cn.cat.netty.demo.heart.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ClientHeartbeatHandler extends IdleStateHandler {
    // 设置写事件为5s
    // 如果5s没有写事件发生 就会触发下面的IdleStateEvent
    private static final int WRITE_IDLE_TIME = 5;

    public ClientHeartbeatHandler() {
        super(0, WRITE_IDLE_TIME, 0, TimeUnit.SECONDS);
    }


    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt)  {
        // 指定时间内没有写事件发送 就会触发 IdleState.WRITER_IDLE 类型事件
        // 我们就可以对该连接进行处理 主动发送心跳
        if (evt.state() == IdleState.WRITER_IDLE) {
            System.out.println("客户端发送心跳包");
            ctx.writeAndFlush("我还活着，不要断开连接阿！！！");
        }
    }
}
