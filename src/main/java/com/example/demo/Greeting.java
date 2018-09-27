package com.example.demo;

public class Greeting {
    private long id;
    private final String content;

    public Greeting(long id, String content) {
        
        this.content = content;

    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


}
