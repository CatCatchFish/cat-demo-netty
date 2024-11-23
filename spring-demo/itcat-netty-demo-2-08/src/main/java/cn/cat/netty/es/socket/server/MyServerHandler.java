package cn.cat.netty.es.socket.server;

import cn.cat.netty.es.domain.TransportProtocol;
import cn.cat.netty.es.domain.User;
import cn.cat.netty.es.service.IUserService;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Resource
    private IUserService userService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        log.info("链接报告信息：有一客户端链接到本服务端");
        log.info("链接报告IP:{}", channel.localAddress().getHostString());
        log.info("链接报告Port:{}", channel.localAddress().getPort());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开链接{}", ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 服务端接收到消息：" + JSON.toJSONString(msg));
        //接收数据写入到Elasticsearch
        TransportProtocol transportProtocol = (TransportProtocol) msg;
        userService.save((User) transportProtocol.getObj());
    }

    /**
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.info("异常信息：\r\n" + cause.getMessage());
    }
}
