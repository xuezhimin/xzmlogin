package com.qh.xuezhimin.week0220181207.bean;

import java.util.List;

public class Root {
    private String msg;

    private int code;

    private List<Data> data ;

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
}
