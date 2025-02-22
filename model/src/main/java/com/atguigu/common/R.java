package com.atguigu.common;

import lombok.Data;

@Data
public class R {

    private Integer code;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.setCode(200);
        r.setMsg("ok");
        r.setData(null);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(200);
        r.setMsg("ok");
        r.setData(data);
        return r;
    }

    public static R ok(Object data, String msg) {
        R r = new R();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(500);
        r.setMsg("error");
        r.setData(null);
        return r;
    }

    public static R error(Object data) {
        R r = new R();
        r.setCode(500);
        r.setMsg("error");
        r.setData(data);
        return r;
    }

    public static R error(Object data, String msg) {
        R r = new R();
        r.setCode(500);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
