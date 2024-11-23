package cn.cat.netty.es.trigger;

import cn.cat.netty.es.socket.server.NettyServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("/localAddress")
    public String localAddress() {
        return "nettyServer localAddress " + nettyServer.getChannel().localAddress();
    }

}