package com.example.a2_1155145472;


public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private String time;
    private int type;

    public Msg(String content, int type,String time) {
        this.content = content+"\n"+time;
        this.type = type;
        this.time = time;

    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;

    }

}