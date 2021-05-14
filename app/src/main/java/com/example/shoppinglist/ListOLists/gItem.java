package com.example.shoppinglist.ListOLists;

public class gItem {
    private String type;
    private String name;
    public  String date;
    public boolean crossed = false;

    public gItem(String id, String content, String details) {
        this.setType(id);
        this.setName(content);
        this.date = details;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getType() +"\\1"+ getName() +"\\2"+date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String toString) {
        this.date = toString;
    }
}
