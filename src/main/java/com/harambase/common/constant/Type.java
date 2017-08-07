package com.harambase.common.constant;

public enum Type {

    STUDENT("s",1),
    FACULTY("f",2),
    ADMINISTRATOR("a",3);

    // 成员变量
    private String m;
    private int v;

    Type(String m , int v){
        this.m = m;
        this.v = v;
    }

    public String getM() {
        return m;
    }

    public int getV() {
        return v;
    }
}
