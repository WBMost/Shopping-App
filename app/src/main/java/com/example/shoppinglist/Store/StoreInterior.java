package com.example.shoppinglist.Store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreInterior#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreInterior extends Fragment {

    private Store mStore;

    public StoreInterior(Store store) {
        mStore = store;
    }

    public static StoreInterior newInstance(Store store) {
        return new StoreInterior(store);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.store_interior, container, false);
    }
}