package com.example.shoppinglist.ListOLists;

import java.util.ArrayList;
import java.util.Collections;

public class gList {
    public String type;
    public String name;
    public String date;
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

    public ArrayList<gItem> alphaSortName()
    {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<gItem> temper = new ArrayList<>();
        for (gItem I : ITEMS)
            temp.add(I.getName());
        Collections.sort(temp);
        for(int x = 0; x < temp.size(); x++)
        {
            for(int i=0; i < ITEMS.size();i++)
            {
                if (ITEMS.get(i).getName().equals(temp.get(x))){
                    temper.add(ITEMS.get(i));
                    break;
                }
            }
        }
        return temper;
    }

    public ArrayList<gItem> alphaSortType()
    {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<gItem> temper = new ArrayList<>();
        for (gItem I : ITEMS)
            if (!temp.contains(I.getType()))
                temp.add(I.getType());
        Collections.sort(temp);
        for(int x = 0; x < temp.size(); x++)
        {
            for(int i=0; i < ITEMS.size();i++)
            {
                if (ITEMS.get(i).getType().equals(temp.get(x)))
                    temper.add(ITEMS.get(i));
            }
        }
        return temper;
    }

    public ArrayList<gItem> alphaSortDate()
    {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<gItem> temper = new ArrayList<>();
        for (gItem I : ITEMS)
            temp.add(I.date);
        Collections.sort(temp);
        for(int x = 0; x < temp.size(); x++)
        {
            for(int i=0; i < ITEMS.size();i++)
            {
                if (ITEMS.get(i).date.equals(temp.get(x))){
                    temper.add(ITEMS.get(i));
                    break;
                }
            }
        }
        return temper;
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
