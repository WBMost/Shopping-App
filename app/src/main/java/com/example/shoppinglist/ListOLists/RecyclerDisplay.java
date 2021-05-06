package com.example.shoppinglist.ListOLists;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoppinglist.R;

public class RecyclerDisplay extends Fragment {
    private groLists mList;
    private gList gList;

    public RecyclerDisplay(groLists list) {
        mList = list;
    }
    public static RecyclerDisplay newInstance(groLists list) { return new RecyclerDisplay(list); }

    public RecyclerDisplay(gList list) {
        gList = list;
    }
    public static RecyclerDisplay newInstance(gList list) { return new RecyclerDisplay(list); }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(gList==null)
            return inflater.inflate(R.layout.list_page, container, false);
        else {
            return inflater.inflate(R.layout.grocery_page, container, false);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(gList==null)
            getChildFragmentManager().beginTransaction().replace(R.id.frameLayout, ListsFragment.newInstance(mList)).commitNow();
        else
            getChildFragmentManager().beginTransaction().replace(R.id.groFrameLayout, EditLists.newInstance(gList)).commitNow();
    }
}