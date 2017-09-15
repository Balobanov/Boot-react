package com.balobanov.data.models;

public class Credit {
    private String name;
    private int status;

    public Credit(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Credit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

