package com.example.shoppinglist.Store;

import java.util.ArrayList;
import java.util.Calendar;

public class Store {
    public ArrayList<Aisle> Layout;
    String name;
    String type;
    String date;

    public Store()
    {
        Layout = new ArrayList<Aisle>();
        name = "unnamed";
        type = "Grocery";
        date = Calendar.getInstance().getTime().toString();
    }

    public Store(String id, String mType)
    {
        Layout = new ArrayList<Aisle>();
        name = id;
        type = mType;
        date = Calendar.getInstance().getTime().toString();
    }

    public Store(String id, String mType, String dateC)
    {
        Layout = new ArrayList<Aisle>();
        name = id;
        type = mType;
        date = dateC;
    }

    public Store(ArrayList<Aisle> layout, String id, String mType)
    {
        Layout = layout;
        name = id;
        type = mType;
        date = Calendar.getInstance().getTime().toString();
    }

    public void updateStore(String id, String mType){
        name = id;
        type = mType;
        date = Calendar.getInstance().getTime().toString();
    }

    public void updateStore(ArrayList<Aisle> layout){
        Layout = layout;
        date = Calendar.getInstance().getTime().toString();
    }

    public void updateStore(ArrayList<Aisle> layout, String id, String mType){
        Layout = layout;
        name = id;
        type = mType;
        date = Calendar.getInstance().getTime().toString();
    }

    public void updateStore(Store other, String id, String mType){
        Layout = other.Layout;
        name = id;
        type = mType;
        date = Calendar.getInstance().getTime().toString();
    }

    @Override
    public String toString() {
        return name+"\\1"+type+"\\2"+date;
    }

    public static class Aisle{
        String name;
        String type;
        int posX,posY;
        int width,length;

        public Aisle()
        {
            String name = "unnamed";
            String type = "Fruit";
            posX = 0;
            posY = 0;
            width = 1;
            length = 1;
        }

        public Aisle(String mType, String mName, String x, String y, String l, String w)
        {
            String name = mName;
            String type = mType;
            posX = (int)Integer.parseInt(x);
            posY = (int)Integer.parseInt(y);
            width = (int)Integer.parseInt(w);
            length = (int)Integer.parseInt(l);
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getLength() {
            return length;
        }

        public int getWidth() {
            return width;
        }

        public int getPosX() {
            return posX;
        }

        public int getPosY() {
            return posY;
        }

        @Override
        public String toString() {
            return type+"\\1"+name+"\\2"+posX+"\\3"+posY+"\\4"+length+"\\5"+width;
        }
    }
}

