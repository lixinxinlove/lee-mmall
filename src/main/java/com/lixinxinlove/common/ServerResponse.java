package com.lixinxinlove.common;

import java.io.Serializable;

/**
 * 通用返回json 格式
 * @param <T>
 */
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;


    private ServerResponse(int status) {
        this.status = status;

    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }


}
