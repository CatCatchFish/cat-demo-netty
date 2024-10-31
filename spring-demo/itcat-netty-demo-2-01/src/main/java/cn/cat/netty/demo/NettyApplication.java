package cn.cat.netty.demo;

import cn.cat.netty.demo.server.NettyServer;
import io.netty.channel.ChannelFuture;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class NettyApplication implements CommandLineRunner {
    @Value("${netty.host}")
    private String host;
    @Value("${netty.port}")
    private int port;
    @Resource
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(host, port);
        ChannelFuture channelFuture = nettyServer.bing(address);
        // 钩子函数，在JVM关闭时，关闭Netty服务
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            nettyServer.destroy();
        }));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
