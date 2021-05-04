package com.example.shoppinglist.ListOLists;

public class gItem {
    public final String type;
    public final String name;
    public final String date;

    public gItem(String id, String content, String details) {
        this.type = id;
        this.name = content;
        this.date = details;
    }

    @Override
    public String toString() {
        return type +"\\1"+name+"\\2"+date;
    }
}
