package cn.cat.netty.demo.sync.test;

import cn.cat.netty.demo.sync.server.ServerSocket;

public class ServerTest {
    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
        System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈，获取源码}");
    }
}
