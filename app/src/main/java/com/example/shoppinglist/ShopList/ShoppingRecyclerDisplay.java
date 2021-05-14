package com.example.shoppinglist.ShopList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppinglist.ListOLists.EditLists;
import com.example.shoppinglist.ListOLists.ListsFragment;
import com.example.shoppinglist.ListOLists.gList;
import com.example.shoppinglist.ListOLists.groLists;
import com.example.shoppinglist.R;
import com.example.shoppinglist.Store.Store;

public class ShoppingRecyclerDisplay extends Fragment {
    private gList mList;
    private Store mStore;

    public ShoppingRecyclerDisplay(gList list) {
        mList = list;
    }
    public static ShoppingRecyclerDisplay newInstance(gList list) { return new ShoppingRecyclerDisplay(list); }

    public ShoppingRecyclerDisplay(gList list, Store store) {
        mList = list;
    }
    public static ShoppingRecyclerDisplay newInstance(gList list, Store store) { return new ShoppingRecyclerDisplay(list); }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shopping_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction().replace(R.id.groFrameLayout, Shopping.newInstance(mList, this)).commitNow();
    }
}