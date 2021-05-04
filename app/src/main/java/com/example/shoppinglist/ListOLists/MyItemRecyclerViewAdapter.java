package com.example.shoppinglist.ListOLists;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoppinglist.Activity;
import com.example.shoppinglist.ListOLists.dummy.DummyContent.DummyItem;
import com.example.shoppinglist.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    List mValues;
    static int itemSel=-1;

    public MyItemRecyclerViewAdapter(List items) { mValues = items; itemSel=-1; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(((gItem) mValues.get(position)).type);
        holder.mContentView.setText((((gItem) mValues.get(position)).name));
        holder.mDateView.setText((((gItem) mValues.get(position)).date));

        //check string length to fit on screen
        if(((gItem) mValues.get(position)).name.length()<21)
            holder.mContentView.setText(((gItem) mValues.get(position)).name);
        else
            holder.mContentView.setText(((gItem) mValues.get(position)).name.substring(0,19)+"...");

        if(itemSel!=position)//checks if item was selected or not
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        if(itemSel==position)//checks if item was selected or not
            holder.cardView.setBackgroundColor(Color.parseColor("#f0f0f0"));

        holder.mMainLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (!holder.selected) {
                    holder.cardView.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    Activity.setTempPostion(position);
                } else {
                    Activity.setTempPostion(-1);
                    holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                holder.selected = !holder.selected;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mIdView;
        public TextView mContentView;
        public TextView mDateView;
        ConstraintLayout mMainLayout;
        CardView cardView;
        public Object mItem;

        boolean selected = false;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_type);
            mContentView = (TextView) view.findViewById(R.id.item_name);
            mDateView = view.findViewById(R.id.item_desc);
            mMainLayout = view.findViewById(R.id.bigOlBoy);
            cardView = view.findViewById(R.id.cardBoy);
            selected = false;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}