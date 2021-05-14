package com.example.shoppinglist.Store;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.shoppinglist.Activity;

import java.util.ArrayList;

public class StoreCanvas extends View {

    ArrayList<Store.Aisle> mAisles;

    public StoreCanvas(Context context) {
        super(context);
        this.setMinimumWidth(context.getWallpaperDesiredMinimumWidth());
        mAisles = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.setMinimumWidth(500);
        Store store = Activity.listOstores.get(Activity.storeTempPosition);
        for(Store.Aisle aisle: store.Layout) {
            mAisles.add(aisle);
            canvas.drawRect(aisle.shape.left * Activity.scale, aisle.shape.top * Activity.scale, aisle.shape.right * Activity.scale, aisle.shape.bottom * Activity.scale, aisle.paint);
        }
    }
}
