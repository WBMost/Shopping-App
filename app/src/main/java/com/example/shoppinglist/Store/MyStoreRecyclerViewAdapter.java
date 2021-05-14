package com.example.shoppinglist.Store;

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

import java.util.ArrayList;
import java.util.List;

public class MyStoreRecyclerViewAdapter extends RecyclerView.Adapter<MyStoreRecyclerViewAdapter.ViewHolder> {

    private final List<Store> mValues;
    MyStoreRecyclerViewAdapter maybe = this;

    public MyStoreRecyclerViewAdapter(ArrayList<Store> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).type);
        holder.mContentView.setText(mValues.get(position).name);
        holder.mDateView.setText(mValues.get(position).date);

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
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Store mItem;
        public TextView mDateView;
        ConstraintLayout mMainLayout;
        CardView cardView;

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