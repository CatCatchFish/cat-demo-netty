package cn.cat.netty.demo.util;

import cn.cat.netty.demo.domain.MsgInfo;

public class MsgUtil {
    public static MsgInfo buildMsg(String channelId, String msg) {
        return new MsgInfo(channelId, msg);
    }
}
