package com.example.shoppinglist.Store;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.Calendar;

public class Store {
    public ArrayList<Aisle> Layout;
    public String name;
    public String type;
    public String date;

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
        public String name;
        public String type;
        public Rect shape;
        public Paint paint;

        public Aisle()
        {
            name = "Fruit";
            type = "Fruit";
            shape = new Rect(0,0,50,150);
            paint = new Paint();
            paint.setStrokeWidth(3);
            paint.setColor(Color.parseColor("#B0B0B0"));
        }

        public Aisle(String mType, String mName, String x, String y, String l, String w)
        {
            name = mName;
            type = mType;
            int posX = (int)Integer.parseInt(x);
            int posY = (int)Integer.parseInt(y);

            shape = new Rect(posX, posY, posX+(int)Integer.parseInt(w), posY+(int)Integer.parseInt(l));
            paint = new Paint();
            paint.setStrokeWidth(3);
            paint.setColor(Color.parseColor("#B0B0B0"));
        }

        public void setColor(String c) {
            paint.setColor(Color.parseColor(c));
        }

        @Override
        public String toString() {
            return type+"\\1"+name+"\\2"+shape.left+"\\3"+shape.top+"\\4"+shape.right+"\\5"+shape.bottom;
        }
    }
}

