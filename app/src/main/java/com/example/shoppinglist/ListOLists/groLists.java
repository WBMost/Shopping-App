package com.example.shoppinglist.ListOLists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class groLists {

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<gList> G_LISTS = new ArrayList<gList>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, gList> ITEM_MAP = new HashMap<String, gList>();

    public static void addItem(gList item) {
        G_LISTS.add(item);
        ITEM_MAP.put(item.type, item);
    }

    /**
     * List as just a basic item.
     */
}