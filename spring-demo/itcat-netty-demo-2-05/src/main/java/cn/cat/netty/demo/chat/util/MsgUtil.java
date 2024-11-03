package cn.cat.netty.demo.chat.util;

import cn.cat.netty.demo.chat.domain.ServerMsgProtocol;
import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MsgUtil {
    public static TextWebSocketFrame buildMsgAll(String channelId, String msgInfo) {
        int i = Math.abs(channelId.hashCode()) % 10;
        ServerMsgProtocol msg = ServerMsgProtocol.builder()
                .type(2)
                .channelId(channelId)
                .userHeadImg("head" + i + ".jpg")
                .msgInfo(msgInfo)
                .build();
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }

    public static TextWebSocketFrame buildMsgOwner(String channelId) {
        ServerMsgProtocol msg = new ServerMsgProtocol();
        msg.setType(1); //链接信息;1链接信息、2消息信息
        msg.setChannelId(channelId);
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }
}
