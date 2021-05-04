package com.example.shoppinglist.ListOLists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class groLists {


    public static ArrayList<gList> G_LISTS = new ArrayList<gList>();


    public static final Map<String, gList> ITEM_MAP = new HashMap<String, gList>();

    public static void addItem(gList item) {
        G_LISTS.add(item);
        ITEM_MAP.put(item.type, item);
    }

}