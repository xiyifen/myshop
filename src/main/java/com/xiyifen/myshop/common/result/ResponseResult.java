package com.xiyifen.myshop.common.result;

import lombok.Data;

import java.util.HashMap;

@Data
class Meta{
    private String msg;
    private int status;

    public Meta(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }
}

@Data
public class ResponseResult  extends HashMap<String,Object>{

    private Object data;
    private    Meta meta;

    public ResponseResult(){

    }

    public ResponseResult( int status,String msg,Object data) {
        this.data = data;
        this.meta = new Meta(msg,status);
    }

    public ResponseResult success(String msg,Object data){
        meta=new Meta(msg,200);
        this.put("data",data);
        this.put("meta",meta);
        return this;
    }

    public ResponseResult error(int status,String msg){
        meta=new Meta(msg,status);
        this.put("data",null);
        this.put("meta",meta);
        return this;
    }
}
