package com.zking.zkingedu.common.utils;

public class ResultUtil {

    private Integer code;//状态吗
    private Object msg;//提示信息
    private String count;//总行数
    private Object data;//响应数据

    public ResultUtil(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultUtil(Integer code, Object msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultUtil(Integer code){
        this.code = code;
    }

    public ResultUtil(Integer code, Object msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultUtil(Integer code, Object msg, String count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
