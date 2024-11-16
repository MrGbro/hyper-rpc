package com.homeey.hyper.protocol;

import java.io.Serializable;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 22:51 2024-11-16
 **/
public class MessageHeader implements Serializable {
    /**
     * 消息长度
     */
    private int msgLength;
    /**
     * 消息类型：请求 | 响应 | 心跳
     */
    private byte msgType;
    /**
     * 消息状态 区分消息 成功 | 超市 | 失败
     */
    private byte status;
    /**
     * 序列化类型，不超过16字节
     */
    private String serializer;
    /**
     * 请求id
     */
    private long requestId;
    /**
     * 魔数
     */
    private final short magic = 0x1234;

    public MessageHeader() {
    }

    public MessageHeader(int msgLength,
                         byte msgType,
                         byte status,
                         String serializer,
                         long requestId) {
        this.msgLength = msgLength;
        this.msgType = msgType;
        this.status = status;
        this.serializer = serializer;
        this.requestId = requestId;
    }

    public int getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(int msgLength) {
        this.msgLength = msgLength;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getSerializer() {
        return serializer;
    }

    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public short getMagic() {
        return magic;
    }
}
