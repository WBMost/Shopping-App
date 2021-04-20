package com.example.shoppinglist.ListOLists;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.MainMenu;
import com.example.shoppinglist.R;

public class ListDisplay extends Fragment {

    public static ListDisplay newInstance() {
        return new ListDisplay();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction().replace(R.id.frameLayout, ListsFragment.newInstance(1)).commitNow();
    }
}