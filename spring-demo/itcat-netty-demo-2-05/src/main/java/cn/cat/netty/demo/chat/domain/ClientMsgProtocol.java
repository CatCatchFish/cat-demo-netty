package cn.cat.netty.demo.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientMsgProtocol {
    private int type;       //1请求个人信息，2发送聊天信息
    private String msgInfo; //消息
}
