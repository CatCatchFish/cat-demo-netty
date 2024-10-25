package cn.cat.netty.demo.nio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

public class NioServer {
    private static final Logger logger = LoggerFactory.getLogger(NioServer.class);
    private Selector selector;
    private ServerSocketChannel socketChannel;

    public static void main(String[] args) {
        NioServer server = new NioServer();
        server.bind(7397);
    }

    public void bind(int port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("服务器启动成功，监听端口: " + port);
            new NioServerHandler(selector, Charset.forName("GBK")).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
