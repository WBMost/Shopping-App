package com.example.shoppinglist.ShopList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.shoppinglist.Activity;
import com.example.shoppinglist.ListOLists.gItem;
import com.example.shoppinglist.R;

public class ItemEdit extends DialogFragment {
    gItem mItem;

    public ItemEdit(gItem gItem) {
        mItem = gItem;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_edit_item, null))
                // Add action buttons
                .setPositiveButton("Global", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //set the item edited everywhere
                        Dialog dialogObj = Dialog.class.cast(dialog);
                        EditText typeText = dialogObj.findViewById(R.id.shopping_ItemType);
                        Activity.gListSelected.ITEMS.get(Activity.tempPosition).setType(typeText.getText().toString());
                        EditText nameText = dialogObj.findViewById(R.id.shopping_ItemName);
                        Activity.gListSelected.ITEMS.get(Activity.tempPosition).setName(nameText.getText().toString());

                        //temp changes stick around
                        Activity.tempgListSelected.ITEMS.get(Activity.tempPosition).setType(typeText.getText().toString());
                        Activity.tempgListSelected.ITEMS.get(Activity.tempPosition).setName(nameText.getText().toString());

                        //temp list rearrange
                        Activity.tempgListSelected.ITEMS = Activity.tempgListSelected.alphaSortName();
                        Activity.tempgListSelected.ITEMS = Activity.tempgListSelected.alphaSortType();

                        //redisplays list
                        Activity.gListSelected.ITEMS = Activity.gListSelected.alphaSortName();
                        Activity.gListSelected.ITEMS = Activity.gListSelected.alphaSortType();

                        getParentFragmentManager().beginTransaction().replace(R.id.container, ShoppingRecyclerDisplay.newInstance(Activity.tempgListSelected)).commitNow();
                        TextView selectedList = getActivity().findViewById(R.id.shopping_ListSelect);
                        TextView selectedStore = getActivity().findViewById(R.id.shopping_StoreSelect);
                        if(selectedList!=null && Activity.gListSelected!=null)
                        {
                            selectedList.setText(Activity.gListSelected.name);
                        }
                        if(selectedStore!=null && Activity.gStoreSelected!=null)
                        {
                            selectedStore.setText(Activity.gStoreSelected.name);
                        }
                        Activity.tempPosition=-1;
                    }
                })
                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ItemEdit.this.getDialog().cancel();
                    }
                })
                .setNegativeButton("local", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //set the item to only be edited in this list
                        Dialog dialogObj = Dialog.class.cast(dialog);
                        EditText typeText = dialogObj.findViewById(R.id.shopping_ItemType);
                        Activity.tempgListSelected.ITEMS.get(Activity.tempPosition).setType(typeText.getText().toString());
                        EditText nameText = dialogObj.findViewById(R.id.shopping_ItemName);
                        Activity.tempgListSelected.ITEMS.get(Activity.tempPosition).setName(nameText.getText().toString());

                        //redisplays list
                        Activity.tempgListSelected.ITEMS = Activity.tempgListSelected.alphaSortName();
                        Activity.tempgListSelected.ITEMS = Activity.tempgListSelected.alphaSortType();
                        getParentFragmentManager().beginTransaction().replace(R.id.container, ShoppingRecyclerDisplay.newInstance(Activity.tempgListSelected)).commitNow();
                        TextView selectedList = getActivity().findViewById(R.id.shopping_ListSelect);
                        TextView selectedStore = getActivity().findViewById(R.id.shopping_StoreSelect);
                        if(selectedList!=null && Activity.tempgListSelected!=null)
                        {
                            selectedList.setText(Activity.tempgListSelected.name);
                        }
                        if(selectedStore!=null && Activity.gStoreSelected!=null)
                        {
                            selectedStore.setText(Activity.gStoreSelected.name);
                        }
                        Activity.tempPosition=-1;
                    }
                });
        View content =  inflater.inflate(R.layout.dialog_edit_item, null);
        builder.setView(content);
        EditText nameText = content.findViewById(R.id.shopping_ItemName);
        EditText typeText = content.findViewById(R.id.shopping_ItemType);
        nameText.setText(mItem.getName());
        typeText.setText(mItem.getType());
        return builder.create();
    }
}
