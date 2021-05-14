package com.example.shoppinglist.ListOLists;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.shoppinglist.Activity;
import com.example.shoppinglist.R;
import com.example.shoppinglist.RecyclerDisplay;

import java.util.Calendar;

public class gItemEdit extends DialogFragment {
    gItem mItem;

    public gItemEdit(gItem gItem) {
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
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //set the item edited everywhere
                        Dialog dialogObj = Dialog.class.cast(dialog);
                        EditText typeText = dialogObj.findViewById(R.id.shopping_ItemType);
                        Activity.listOlists.G_LISTS.get(Activity.listTempPosition).ITEMS.get(Activity.tempPosition).setType(typeText.getText().toString());
                        EditText nameText = dialogObj.findViewById(R.id.shopping_ItemName);
                        Activity.listOlists.G_LISTS.get(Activity.listTempPosition).ITEMS.get(Activity.tempPosition).setName(nameText.getText().toString());
                        Activity.listOlists.G_LISTS.get(Activity.listTempPosition).ITEMS.get(Activity.tempPosition).setDate(Calendar.getInstance().getTime().toString());

                        getParentFragmentManager().beginTransaction().replace(R.id.container, RecyclerDisplay.newInstance(Activity.listOlists.G_LISTS.get(Activity.listTempPosition))).commitNow();
                        EditText changeListName = getActivity().findViewById(R.id.currentListName);
                        EditText changeListType = getActivity().findViewById(R.id.currentListType);
                        if(changeListName!=null)
                        {
                            changeListName.setText(Activity.listOlists.G_LISTS.get(Activity.listTempPosition).name);
                        }
                        if(changeListType!=null)
                        {
                            changeListType.setText(Activity.listOlists.G_LISTS.get(Activity.listTempPosition).type);
                        }
                        Activity.tempPosition=-1;
                    }
                })
                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        gItemEdit.this.getDialog().cancel();
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
