package cn.cat.netty.demo.test;

import cn.cat.netty.demo.server.NettyServer;

public class NettyServerTest {
    public static void main(String[] args) {
        //启动服务
        new NettyServer().bing(7397);
    }
}
