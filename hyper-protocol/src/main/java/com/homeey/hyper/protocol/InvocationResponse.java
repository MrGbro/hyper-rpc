package com.homeey.hyper.protocol;

import java.io.Serializable;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 22:49 2024-11-16
 **/
public class InvocationResponse implements Serializable {
    /**
     * 错误信息
     */
    private String error;
    /**
     * 结果对象
     */
    private Object result;

    public InvocationResponse() {
    }

    public InvocationResponse(String error, Object result) {
        this.error = error;
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
