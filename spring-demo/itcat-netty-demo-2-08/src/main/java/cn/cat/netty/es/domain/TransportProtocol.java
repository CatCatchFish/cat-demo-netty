package cn.cat.netty.es.domain;

import lombok.Getter;

@Getter
public class TransportProtocol {
    private Integer type; //1用户信息
    private Object obj;   //传输对象

    public TransportProtocol() {
    }

    public TransportProtocol(Integer type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
