package cn.cat.netty.demo.sync.msg;

import lombok.Data;

@Data
public class Request {
    private String requestId;
    private Object result;
}
