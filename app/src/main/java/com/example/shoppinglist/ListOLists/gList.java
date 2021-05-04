package com.example.shoppinglist.ListOLists;

import java.util.ArrayList;

public class gList {
    public final String type;
    public final String name;
    public final String date;
    public ArrayList<gItem> ITEMS = new ArrayList<gItem>();

    public gList()
    {
        this.type ="default";
        this.name="default";
        this.date="default";
        this.ITEMS = new ArrayList<gItem>();
    }

    public gList(String id, String content, String cDate) {
        this.type = id;
        this.name = content;
        this.date = cDate;
        this.ITEMS = new ArrayList<gItem>();
    }

    public void addGItem(gItem gI)
    {
        this.ITEMS.add(gI);
    }
    private ArrayList<gItem> getItems(){return this.ITEMS;}

    public ArrayList<gItem> getITEMS() {
        return ITEMS;
    }

    @Override
    public String toString() {
        return type+"\\1"+name+"\\2"+date;
    }


}
