package com.example.googlemap;

public class TinTuc {
    public String id;
    public String userid;
    public String content;
    public String time;
    public String name;

    public TinTuc() {

    }

    public TinTuc(String id, String userid, String content, String time, String name) {
        this.id = id;
        this.userid = userid;
        this.content = content;
        this.time = time;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
