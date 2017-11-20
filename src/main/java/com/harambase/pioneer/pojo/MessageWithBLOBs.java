package com.harambase.pioneer.pojo;

public class MessageWithBLOBs extends Message {
    private String body;



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }


}