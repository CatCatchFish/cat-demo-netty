package cn.cat.netty.demo.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerMsgProtocol {
    private int type;             //链接信息;1自发信息、2群发消息
    private String channelId;     //通信管道ID，实际使用中会映射成用户名
    private String userHeadImg;   //用户头像[模拟分配]
    private String msgInfo;       //通信消息
}
