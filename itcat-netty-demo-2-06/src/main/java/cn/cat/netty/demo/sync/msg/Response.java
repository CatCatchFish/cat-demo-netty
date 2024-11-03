package cn.cat.netty.demo.sync.msg;

import lombok.Data;

@Data
public class Response {
    private String requestId;
    private String param;
}
