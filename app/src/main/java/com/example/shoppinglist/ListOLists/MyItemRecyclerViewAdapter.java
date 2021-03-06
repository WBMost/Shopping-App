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
import com.example.shoppinglist.R;

import java.util.List;

/**
 *
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    List mValues;
    static int itemSel=-1;
    MyItemRecyclerViewAdapter maybe = this;

    public MyItemRecyclerViewAdapter(List items) { mValues = items; itemSel=-1; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(((gItem) mValues.get(position)).getType());
        holder.mContentView.setText((((gItem) mValues.get(position)).getName()));
        holder.mDateView.setText((((gItem) mValues.get(position)).date));

        //check string length to fit on screen
        if(((gItem) mValues.get(position)).getName().length()<21)
            holder.mContentView.setText(((gItem) mValues.get(position)).getName());
        else
            holder.mContentView.setText(((gItem) mValues.get(position)).getName().substring(0,19)+"...");

        if (Activity.tempPosition==position)
            holder.cardView.setBackgroundColor(Color.parseColor("#F0F0F0"));
        else
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        holder.mMainLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(Activity.tempPosition==position)
                    Activity.setTempPosition(-1);
                else
                    Activity.setTempPosition(position);
                maybe.notifyDataSetChanged();
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