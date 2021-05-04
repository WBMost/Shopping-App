package com.example.shoppinglist.ListOLists;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.ListOLists.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EditLists extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private EditText name;
    private EditText type;
    private gList listo;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EditLists(gList list) {
        listo = list;
        /*name = getActivity().findViewById(R.id.currentListName);
        type = getActivity().findViewById(R.id.currentListType);
        name.setText(listo.name, TextView.BufferType.EDITABLE);
        type.setText(listo.type, TextView.BufferType.EDITABLE);*/
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EditLists newInstance(gList list) {
        EditLists fragment = new EditLists(list);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
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
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(listo.getITEMS()));
        }
        return view;
    }
}