package cn.cat.netty.demo.sync.future;

import cn.cat.netty.demo.sync.msg.Response;

import java.util.concurrent.Future;

public interface WriteFuture<T> extends Future<T> {
    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(Response response);

    boolean isTimeout();
}
