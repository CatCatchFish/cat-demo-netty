package cn.cat.netty.demo.sync.future;

import cn.cat.netty.demo.sync.msg.Request;
import cn.cat.netty.demo.sync.msg.Response;
import io.netty.channel.Channel;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncWrite {
    public Response writeAndSync(final Channel channel, final Request request, final long timeout) throws Exception {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout <= 0");
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);

        WriteFuture<Response> future = new SyncWriteFuture(request.getRequestId());
        SyncWriteMap.syncKey.put(requestId, future);

        Response response = doWriteAndSync(channel, request, timeout, future);
        SyncWriteMap.syncKey.remove(requestId);
        return response;
    }

    private Response doWriteAndSync(final Channel channel, final Request request, final long timeout, final WriteFuture<Response> writeFuture) throws Exception {
        channel.writeAndFlush(request).addListener(channelFuture -> {
            writeFuture.setWriteResult(channelFuture.isSuccess());
            writeFuture.setCause(channelFuture.cause());
            if (!writeFuture.isWriteSuccess()) {
                SyncWriteMap.syncKey.remove(writeFuture.requestId());
            }
        });

        Response response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
        if (response == null) {
            if (writeFuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                // write exception
                throw new Exception(writeFuture.cause());
            }
        }
        return response;
    }
}
