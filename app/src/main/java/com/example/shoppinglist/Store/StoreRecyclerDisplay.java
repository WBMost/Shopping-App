package com.example.shoppinglist.Store;

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

import java.util.ArrayList;

public class StoreRecyclerDisplay extends Fragment {
    private ArrayList<Store> mStores;
    private com.example.shoppinglist.ListOLists.gList gList;

    public StoreRecyclerDisplay(ArrayList<Store> stores) {
        mStores = stores;
    }
    public static StoreRecyclerDisplay newInstance(ArrayList<Store> stores) { return new StoreRecyclerDisplay(stores); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.store_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction().replace(R.id.StoreframeLayout, StoreFragment.newInstance(mStores)).commitNow();
    }
}