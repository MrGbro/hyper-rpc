package com.homeey.hyper.protocol;

import java.io.Serializable;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 22:51 2024-11-16
 **/
public class TransportProtocol<T> implements Serializable {
    /**
     * 消息头
     */
    private MessageHeader header;
    /**
     * 消息体
     */
    private T body;

    public TransportProtocol() {
    }

    public TransportProtocol(MessageHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
