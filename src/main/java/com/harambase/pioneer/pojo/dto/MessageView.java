package com.harambase.pioneer.pojo.dto;

import com.harambase.pioneer.pojo.MessageWithBLOBs;

import java.io.Serializable;

public class MessageView extends MessageWithBLOBs implements Serializable {

    private String pic;

    private String sender;

    private String receiver;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}
