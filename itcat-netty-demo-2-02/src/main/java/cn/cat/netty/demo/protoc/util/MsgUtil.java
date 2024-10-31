package cn.cat.netty.demo.protoc.util;

import cn.cat.netty.demo.protoc.domain.MsgBody;

public class MsgUtil {
    public static MsgBody buildMsg(String channelId, String msgInfo) {
        MsgBody.Builder msg = MsgBody.newBuilder();
        msg.setChannelId(channelId);
        msg.setMsgInfo(msgInfo);
        return msg.build();
    }
}
