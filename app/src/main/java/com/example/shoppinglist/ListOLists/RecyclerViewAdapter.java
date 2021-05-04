package com.example.shoppinglist.ListOLists;

import androidx.annotation.NonNull;
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

/**
 * {@link RecyclerView.Adapter} that can display a {@link gList}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    java.util.List mValues;

    public RecyclerViewAdapter(java.util.List items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = (gList) mValues.get(position);
        holder.mIdView.setText(((gList) mValues.get(position)).type);
        holder.mDescView.setText((((gList) mValues.get(position)).date));
        if(((gList) mValues.get(position)).name.length()<21)
            holder.mContentView.setText(((gList) mValues.get(position)).name);
        else
            holder.mContentView.setText(((gList) mValues.get(position)).name.substring(0,19)+"...");
        holder.mMainLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (!holder.selected) {

                    holder.cardView.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    Activity.setTempPosition(position);
                } else {
                    Activity.setTempPosition(-1);
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
        public TextView mDescView;
        ConstraintLayout mMainLayout;
        CardView cardView;
        public Object mItem;

        boolean selected = false;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_type);
            mContentView = (TextView) view.findViewById(R.id.item_name);
            mDescView = (TextView) view.findViewById(R.id.item_desc);
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