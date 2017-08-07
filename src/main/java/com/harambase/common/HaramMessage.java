package com.harambase.common;

import java.util.HashMap;

public class HaramMessage extends HashMap {

    public void setCode(int code){
        super.put("code", code);
    }

    public void setMsg(String msg){
        super.put("msg", msg);
    }

    public int getCode(){
       return (int)super.get("code");
    }

    public String getMsg(){
        return (String)super.get("msg");
    }

    public Object getData() {
        return this.get("data");
    }

    public void setData(Object data) {
        super.put("data", data);
    }
}
