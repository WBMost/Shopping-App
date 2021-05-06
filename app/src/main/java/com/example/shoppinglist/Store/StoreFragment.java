package com.example.shoppinglist.Store;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class StoreFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private ArrayList<Store> store;

    public StoreFragment(ArrayList<Store> mStore) {
        store = mStore;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StoreFragment newInstance(ArrayList<Store> store) {
        StoreFragment fragment = new StoreFragment(store);
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyStoreRecyclerViewAdapter(store));
        }
        return view;
    }
}