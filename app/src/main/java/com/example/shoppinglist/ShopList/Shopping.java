package com.example.shoppinglist.ShopList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.ListOLists.RecyclerViewAdapter;
import com.example.shoppinglist.ListOLists.gList;
import com.example.shoppinglist.ListOLists.groLists;
import com.example.shoppinglist.R;
import com.example.shoppinglist.Store.Store;

import java.util.Collections;

public class Shopping extends Fragment {
    private gList mList;
    private ShoppingRecyclerDisplay mStore;

    public Shopping(gList list, ShoppingRecyclerDisplay yea) {
        mList = list;
        mStore = yea;
    }

    @SuppressWarnings("unused")
    public static Shopping newInstance(gList list, ShoppingRecyclerDisplay yea) {
        Shopping fragment = new Shopping(list, yea);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ShoppingRecyclerViewAdapter(mList));
        }
        return view;
    }
}
