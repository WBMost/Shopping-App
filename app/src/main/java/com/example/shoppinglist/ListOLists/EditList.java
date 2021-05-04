package com.example.shoppinglist.ListOLists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppinglist.R;

public class EditList extends Fragment{
    gList gList;
    public EditList(gList list) { gList = list; }

    public static ListsFragment newInstance(gList list) {
        ListsFragment fragment = new ListsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        /*EditText Type = getView().findViewById(R.id.currentListName);
        EditText Name = getView().findViewById(R.id.currentListType);
        Type.setText("gList.id");
        Name.setText("gList.name");*/
        return inflater.inflate(R.layout.grocery_page, container, false);
    }
}