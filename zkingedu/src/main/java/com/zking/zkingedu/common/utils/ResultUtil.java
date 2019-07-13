package com.zking.zkingedu.common.utils;

public class ResultUtil {

    private Integer code;//状态吗
    private Object msg;//提示信息
    private String count;//总行数
    private Object data;//响应数据


    private Object pages;//总页数

    public Object getPages() {
        return pages;
    }

    public void setPages(Object pages) {
        this.pages = pages;
    }

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


    public ResultUtil(Integer code,Object data){
        this.data = data;
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

    public ResultUtil(Integer code, String count,Object msg,  Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }


    public static ResultUtil ok(Object list){
        ResultUtil result = new ResultUtil();
        result.setCode(0);
        result.setData(list);
        return result;
    }
    public static ResultUtil ok(String msg){
        ResultUtil result = new ResultUtil();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public static ResultUtil error(){
        return new ResultUtil(500,"您的操作过于频繁，请稍后再试 ");
    }
    public static ResultUtil error(String str){
        ResultUtil result = new ResultUtil();
        result.setCode(500);
        result.setMsg(str);
        return result;
    }
}
