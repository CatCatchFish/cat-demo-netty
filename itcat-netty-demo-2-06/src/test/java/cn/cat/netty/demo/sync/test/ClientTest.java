package cn.cat.netty.demo.sync.test;

import cn.cat.netty.demo.sync.client.ClientSocket;
import cn.cat.netty.demo.sync.future.SyncWrite;
import cn.cat.netty.demo.sync.msg.Request;
import cn.cat.netty.demo.sync.msg.Response;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelFuture;

public class ClientTest {
    private static ChannelFuture future;

    public static void main(String[] args) {
        System.out.println("ClientTest");
        ClientSocket client = new ClientSocket();
        new Thread(client).start();

        while (true) {
            try {
                if (null == future) {
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                Request request = new Request();
                request.setResult("查询｛bugstack虫洞栈｝用户信息");
                SyncWrite s = new SyncWrite();
                Response response = s.writeAndSync(future.channel(), request, 1000);
                System.out.println("调用结果：" + JSON.toJSON(response));
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
