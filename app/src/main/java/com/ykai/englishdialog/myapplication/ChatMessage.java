package com.ykai.englishdialog.myapplication;


import java.util.Date;


public class ChatMessage {

    private String name;
    private String message;
    private Type type;
    private Date data;

    public ChatMessage() {

    }

    public ChatMessage(String message, Type type, Date data) {
        super();
        this.message = message;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public enum Type {
        INCOUNT, OUTCOUNT
    }
}
